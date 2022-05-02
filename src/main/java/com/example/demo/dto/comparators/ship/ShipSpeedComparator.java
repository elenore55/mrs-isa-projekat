package com.example.demo.dto.comparators.ship;

import com.example.demo.dto.ShipDTO;

import java.util.Comparator;

public class ShipSpeedComparator implements Comparator<ShipDTO> {
    @Override
    public int compare(ShipDTO s1, ShipDTO s2) {
        return s1.getMaxSpeed().compareTo(s2.getMaxSpeed());
    }
}
