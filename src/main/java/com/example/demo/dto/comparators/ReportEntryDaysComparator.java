package com.example.demo.dto.comparators;

import com.example.demo.dto.ReportEntryDTO;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;

public class ReportEntryDaysComparator implements Comparator<ReportEntryDTO> {

    @Override
    public int compare(ReportEntryDTO r1, ReportEntryDTO r2) {
        String[] elements1 = r1.getX().split(" ");
        String[] elements2 = r2.getX().split(" ");
        if (elements1.length == 3) {
            int day1 = Integer.parseInt(elements1[0]);
            Month month1 = Month.valueOf(elements1[1]);
            int year1 = Integer.parseInt(elements1[2]);
            int day2 = Integer.parseInt(elements2[0]);
            Month month2 = Month.valueOf(elements2[1]);
            int year2 = Integer.parseInt(elements2[2]);
            return LocalDate.of(year1, month1, day1).compareTo(LocalDate.of(year2, month2, day2));
        }
        else {
            int day1 = 1;
            Month month1 = Month.valueOf(elements1[0]);
            int year1 = Integer.parseInt(elements1[1]);
            int day2 = 1;
            Month month2 = Month.valueOf(elements2[0]);
            int year2 = Integer.parseInt(elements2[1]);
            return LocalDate.of(year1, month1, day1).compareTo(LocalDate.of(year2, month2, day2));
        }
    }
}
