package com.example.demo.dto.comparators;

import com.example.demo.dto.CottageDTO;

import java.util.Comparator;

public class CottagePriceComparator implements Comparator<CottageDTO> {

    @Override
    public int compare(CottageDTO c1, CottageDTO c2) {
        return c1.getPrice().compareTo(c2.getPrice());
    }
}
