package com.example.demo.service;

import com.example.demo.model.Offer;
import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private ReservationRepository repository;

    @Autowired
    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
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
        String from = "milica.popovic55@hotmail.com";
        String to = reservation.getClient().getEmail();
        String subject = "Reservation confirmation";
        String text = "Do you confirm";
        new EmailSender(from, to, subject, text).start();
    }
}
