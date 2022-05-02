package com.example.demo.dto.comparators.cottage;

import com.example.demo.model.Cottage;

import java.util.Comparator;

public class CottageNameComparator implements Comparator<Cottage> {

    @Override
    public int compare(Cottage c1, Cottage c2) {
        return c1.getName().compareTo(c2.getName());
    }
}
