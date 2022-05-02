package com.example.demo.dto.comparators.cottage;

import com.example.demo.model.Cottage;

import java.util.Comparator;

public class CottageRoomsComparator implements Comparator<Cottage> {

    @Override
    public int compare(Cottage c1, Cottage c2) {
        Integer n1 = c1.getRooms().size();
        Integer n2 = c2.getRooms().size();
        return n1.compareTo(n2);
    }
}
