package com.example.demo.service;

import com.example.demo.model.Offer;
import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

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
        return true;
    }
}