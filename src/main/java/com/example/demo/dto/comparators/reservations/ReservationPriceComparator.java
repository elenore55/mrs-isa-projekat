package com.example.demo.dto.comparators.reservations;

import com.example.demo.model.Reservation;

import java.util.Comparator;

public class ReservationPriceComparator implements Comparator<Reservation> {
    @Override
    public int compare(Reservation r1, Reservation r2)
    {
        return 1;
    }

}
