package com.example.demo.dto.comparators.cottage;

import com.example.demo.dto.CottageDTO;

import java.util.Comparator;

public class CottageRoomsComparator implements Comparator<CottageDTO> {

    @Override
    public int compare(CottageDTO c1, CottageDTO c2) {
        Integer n1 = c1.getRooms().size();
        Integer n2 = c2.getRooms().size();
        return n1.compareTo(n2);
    }
}
