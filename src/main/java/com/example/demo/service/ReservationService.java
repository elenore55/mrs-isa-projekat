package com.example.demo.service;

import com.example.demo.model.Cottage;
import com.example.demo.model.Offer;
import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public Reservation findOne(Integer id) {
        return repository.findById(id).orElseGet(null);
    }

    public boolean isPossibleReservation(Reservation reservation) {
        Offer offer = reservation.getOffer();
        for (Reservation r : offer.getReservations()) {
            if (!(r.getStart().compareTo(reservation.getEnd()) > 0 || r.getEnd().compareTo(reservation.getStart()) < 0))
                return false;
        }
        return true;
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
}
