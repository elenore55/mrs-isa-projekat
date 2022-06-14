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
import org.springframework.stereotype.Service;

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

    public Reservation save(Reservation reservation) {
        return repository.save(reservation);
    }

    public List<Reservation> findAll() {
        return repository.findAll();
    }

    public Reservation findOne(Integer id) {
        return repository.findById(id).orElseGet(null);
    }

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
        System.out.println("BILA SAM U NOTIFICIRANJUUUUUUUUUUUUUUUUU");
        System.out.println("Email je " + reservation.getClient().getEmail());
        String to = reservation.getClient().getEmail();
        String subject = "Reservation confirmation";
        String text = "To confirm reservation for\n";
        text += reservation.getOffer().getName() + "\nPlease click the link below\n";
        text += "http://localhost:8000/#/confirmReservation/" + reservation.getId().toString();
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
