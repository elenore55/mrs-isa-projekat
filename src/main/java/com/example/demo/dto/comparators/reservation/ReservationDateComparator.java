package com.example.demo.dto.comparators.reservation;

import com.example.demo.model.Reservation;

import java.util.Comparator;

public class ReservationDateComparator implements Comparator<Reservation> {
    @Override
    public int compare(Reservation r1, Reservation r2) {
        return r1.getStart().compareTo(r2.getStart());
    }
}
