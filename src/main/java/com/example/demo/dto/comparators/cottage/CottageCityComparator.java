package com.example.demo.dto.comparators.cottage;

import com.example.demo.model.Cottage;

import java.util.Comparator;

public class CottageCityComparator implements Comparator<Cottage> {
    @Override
    public int compare(Cottage c1, Cottage c2) {
        return c1.getAddress().getCity().compareTo(c2.getAddress().getCity());
    }
}
