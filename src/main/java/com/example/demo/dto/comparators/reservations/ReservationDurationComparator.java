package com.example.demo.dto.comparators.reservations;

import com.example.demo.model.Reservation;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

public class ReservationDurationComparator implements Comparator<Reservation> {
    @Override
    public int compare (Reservation r1, Reservation r2)
    {
        long d1 = Duration.between(r1.getStart(), r1.getEnd()).toMinutes();
        long d2 = Duration.between(r2.getStart(), r2.getEnd()).toMinutes();
        if (d1<d2) return 1;
        return -1;
    }
}
