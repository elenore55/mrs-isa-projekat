package com.example.demo.dto.comparators.ship;

import com.example.demo.dto.ShipDTO;

import java.util.Comparator;

public class ShipLengthComparator implements Comparator<ShipDTO> {
    @Override
    public int compare(ShipDTO s1, ShipDTO s2) {
        return s1.getLength().compareTo(s2.getLength());
    }
}
