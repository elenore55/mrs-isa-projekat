package com.example.demo.dto.comparators;

import com.example.demo.dto.CottageDTO;

import java.util.Comparator;

public class CottageNameComparator implements Comparator<CottageDTO> {

    @Override
    public int compare(CottageDTO c1, CottageDTO c2) {
        return c1.getName().compareTo(c2.getName());
    }
}
