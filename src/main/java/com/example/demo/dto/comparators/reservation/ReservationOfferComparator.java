package com.example.demo.dto.comparators.reservation;

import com.example.demo.model.Reservation;

import java.util.Comparator;

public class ReservationOfferComparator implements Comparator<Reservation> {
    @Override
    public int compare(Reservation r1, Reservation r2) {
        return r1.getOffer().getName().compareTo(r2.getOffer().getName());
    }
}
