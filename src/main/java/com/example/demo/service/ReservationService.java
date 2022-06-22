package com.example.demo.service;

import com.example.demo.model.Availability;
import com.example.demo.model.Offer;
import com.example.demo.model.Reservation;
import com.example.demo.dto.FilterPastDTO;
import com.example.demo.dto.comparators.reservations.ReservationDurationComparator;
import com.example.demo.dto.comparators.reservations.ReservationPriceComparator;
import com.example.demo.dto.comparators.reservations.ReservationStartDateComparator;
import com.example.demo.model.*;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.emailSenders.EmailSender;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository repository;
    private EmailSender emailSender;

    @Autowired
    public ReservationService(ReservationRepository repository, EmailSender emailSender) {
        this.repository = repository;
        this.emailSender = emailSender;
    }

    @Transactional
    public Reservation save(Reservation reservation) {
        try {
            return repository.save(reservation);
        }
        catch(ObjectOptimisticLockingFailureException e) {
            // prvo provjeriti da li je bilo poklapanja termina, ako nije, vratiti vikendicu
            // ako jeste, baciti exception dalje
            if (hasInSamePeriod(reservation))
            {
                throw new ObjectOptimisticLockingFailureException("This entity is already reserved", e);
            }
            else
            {
                Offer o = reservation.getOffer();
                o.addReservation(reservation);
                o.setNumberOfReservations(o.getNumberOfReservations()+1);
                o.setNumberOfPriceLists(o.getNumberOfPriceLists() + 1);
                reservation.setOffer(o);
                o.addReservation(reservation);
                return repository.save(reservation);
            }
        }
    }

    private boolean hasInSamePeriod(Reservation reservation) {
        // ova metoda nam vraca listu svih rezervacija datog entiteta
        /*List<Reservation> offersReservations = repository.getByOfferId(reservation.getOffer().getId());
        for (Reservation r : offersReservations)
        {
            // nema problema ako jedna zavrsi prije nego sto druga pocne i obrnuto
            if (!(r.getEnd().isBefore(reservation.getStart()) || r.getStart().isAfter(reservation.getEnd())))
            {
                return true;
            }
        }*/
        return false;
    }

    @Transactional
    public List<Reservation> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Reservation findOne(Integer id) {
        return repository.findOneById(id);
    }

    @Transactional
    public boolean isPossibleReservation(Reservation reservation) {
        Offer offer = reservation.getOffer();
        for (Reservation r : offer.getReservations()) {
            if (!(r.getStart().compareTo(reservation.getEnd()) > 0 || r.getEnd().compareTo(reservation.getStart()) < 0))
                return false;
        }
        List<Availability> avs = offer.getAvailabilities();
        for (Availability a : avs) {
            if (a.getStart().compareTo(reservation.getStart()) <= 0 && a.getEnd().compareTo(reservation.getEnd()) >= 0) {
                return true;
            }
        }
        return false;
    }

    public void notifyClient(Reservation reservation) {
        System.out.println("Email je " + reservation.getClient().getEmail());
        String to = reservation.getClient().getEmail();
        String subject = "Reservation confirmation";
        String name = reservation.getClient().getName();
        String offerName = reservation.getOffer().getName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String start = formatter.format(reservation.getStart());
        String end = formatter.format(reservation.getEnd());
        String text = String.format("Hello %s!\nThere is a new reservation for you." +
                "\nOffer: %s\nStart date: %s\nEnd date: %s\nEnjoy your stay!", name, offerName, start, end);
        emailSender.send(to, subject, text);
    }


    public List<Reservation> getReservations() {
        return repository.findAll();
    }

    public List<Reservation> getClientsUpcomingReservations(int id) {
        List<Reservation> retVal = new ArrayList<>();
        List<Reservation> reservations = repository.findAll();
        for(Reservation r : reservations)
        {
            if (r.getClient().getId().equals(id) && isInFuture(r))
            {
                retVal.add(r);
            }
        }
        return retVal;
    }

    private boolean isInFuture(Reservation r) {
        LocalDateTime d = r.getStart();
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(d);
    }

    public void cancelReservation(Integer id) {
        repository.cancelReservation(id);
    }

    public List<Reservation> getClientsPastReservations(FilterPastDTO filterPastDTO) {
        List<Reservation> retVal = new ArrayList<>();
        List<Reservation> reservations = repository.findAll();
        for(Reservation r : reservations)
        {
            if (r.getClient().getId().equals(filterPastDTO.getId()) && isInPast(r) && isEntityType(r, filterPastDTO.getSortEntity()))
            {
                retVal.add(r);
            }
        }
        return retVal;
    }

    private boolean isEntityType(Reservation r, int sortEntity) {
        Offer o = r.getOffer();
        switch (sortEntity)
        {
            case 1:
                return o instanceof Cottage;
            case 2:
                return o instanceof Ship;
            case 3:
                return o instanceof Adventure;
            default:
                return true;
        }
    }

    private boolean isAdequateStatus(ReservationStatus r) {
        // ova funkcija se ne poziva nigdje, a mogla bi da se pozove, ali o tome drugi put
        return r.equals(ReservationStatus.FINISHED);
    }

    private boolean isInPast(Reservation r) {
        LocalDateTime d = r.getStart();
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(d);
    }


    public void sortPastReservations(List<Reservation> reservations, int sortBy) {
        Comparator<Reservation> comparator = selectReservationComparator(sortBy);
        reservations.sort(comparator);
        // ovdje potencijalno dodati smjer sortiranja
    }

    private Comparator<Reservation> selectReservationComparator(int sortBy) {
        switch (sortBy)
        {
            case 1:
                return new ReservationStartDateComparator();
            case 2:
                return new ReservationDurationComparator();
            default:
                return new ReservationPriceComparator();
        }
    }
}
