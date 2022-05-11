package com.example.demo.dto.comparators.ship;

import com.example.demo.model.Ship;

import java.util.Comparator;

public class ShipNameComparator implements Comparator<Ship> {
    @Override
    public int compare(Ship s1, Ship s2) {
        return s1.getName().compareTo(s2.getName());
    }
}
