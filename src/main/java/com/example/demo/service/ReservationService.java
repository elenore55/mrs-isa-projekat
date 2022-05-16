package com.example.demo.service;

import com.example.demo.model.Offer;
import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

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
        String to = reservation.getClient().getEmail();
        String subject = "Reservation confirmation";
        String text = "To confirm reservation for\n";
        text += reservation.getOffer().getName() + "\nPlease click the link below\n";
        text += "http://localhost:8000/#/confirmReservation/" + reservation.getId().toString();
        emailSender.send(to, subject, text);
    }
}
