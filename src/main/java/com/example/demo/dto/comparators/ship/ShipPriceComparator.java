package com.example.demo.dto.comparators.ship;

import com.example.demo.dto.ShipDTO;
import com.example.demo.model.Ship;

import java.util.Comparator;

public class ShipPriceComparator implements Comparator<Ship> {
    @Override
    public int compare(Ship s1, Ship s2) {
        return s1.getPriceList().compareTo(s2.getPriceList());
    }
}
