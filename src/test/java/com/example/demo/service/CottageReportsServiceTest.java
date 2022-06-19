package com.example.demo.service;

import com.example.demo.dto.ReportEntryDTO;
import com.example.demo.model.Cottage;
import com.example.demo.model.CottageOwner;
import com.example.demo.model.PriceList;
import com.example.demo.model.Reservation;
import com.example.demo.model.enums.ReservationStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CottageReportsServiceTest {

    private CottageOwner owner;

    @InjectMocks
    private CottageReportsService cottageReportsService;

    @BeforeTestMethod
    public void populate() {
        owner = new CottageOwner();

        Cottage c1 = new Cottage();
        c1.setPriceList(BigDecimal.valueOf(50));
        PriceList p1 = new PriceList(LocalDate.of(2021, 11, 1), BigDecimal.valueOf(50));
        List<PriceList> priceLists1 = new ArrayList<>();
        priceLists1.add(p1);
        c1.setPriceHistory(priceLists1);

        Cottage c2 = new Cottage();
        c2.setPriceList(BigDecimal.valueOf(70));
        PriceList p2 = new PriceList(LocalDate.of(2021, 11, 1), BigDecimal.valueOf(70));
        List<PriceList> priceLists2 = new ArrayList<>();
        priceLists2.add(p2);
        c2.setPriceHistory(priceLists2);

        Cottage c3 = new Cottage();
        c3.setPriceList(BigDecimal.valueOf(100));
        PriceList p3 = new PriceList(LocalDate.of(2021, 11, 1), BigDecimal.valueOf(100));
        List<PriceList> priceLists3 = new ArrayList<>();
        priceLists3.add(p3);
        c3.setPriceHistory(priceLists3);

        Reservation r1 = new Reservation();
        r1.setReservationStatus(ReservationStatus.FINISHED);
        r1.setStart(LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0));
        r1.setEnd(LocalDateTime.of(2022, Month.JANUARY, 10, 0, 0));
        r1.setOffer(c1);

        Reservation r2 = new Reservation();
        r2.setReservationStatus(ReservationStatus.CANCELLED);
        r2.setStart(LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0));
        r2.setEnd(LocalDateTime.of(2022, Month.JANUARY, 10, 0, 0));
        r2.setOffer(c1);

        Reservation r3 = new Reservation();
        r3.setReservationStatus(ReservationStatus.FINISHED);
        r3.setStart(LocalDateTime.of(2022, Month.FEBRUARY, 5, 0, 0));
        r3.setEnd(LocalDateTime.of(2022, Month.FEBRUARY, 10, 0, 0));
        r3.setOffer(c3);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(r1);
        reservations.add(r2);
        reservations.add(r3);
        owner.setReservations(reservations);

        List<Cottage> cottages = new ArrayList<>();
        cottages.add(c1);
        cottages.add(c2);
        cottages.add(c3);
        owner.setCottages(cottages);
    }

    @Test
    public void testCalculateMonthlyIncome() {
        populate();
        List<ReportEntryDTO> reports = cottageReportsService.calculateMonthlyIncome(owner,
                LocalDateTime.of(2022, 1, 1, 0, 0), LocalDateTime.of(2022, 3, 3, 0, 0));

        assertThat(reports).hasSize(3);
        assertEquals(reports.get(0).getY(), BigDecimal.valueOf(450));
        assertEquals(reports.get(1).getY(), BigDecimal.valueOf(500));
        assertEquals(reports.get(2).getY(), BigDecimal.valueOf(0));
    }

    @Test
    public void testCalculateMonthlyVisitReport() {
        populate();
        List<ReportEntryDTO> reports = cottageReportsService.calculateMonthlyVisitReport(owner,
                LocalDateTime.of(2022, 1, 1, 0, 0), LocalDateTime.of(2022, 3, 3, 0, 0));
        assertThat(reports).hasSize(3);
        assertEquals(reports.get(0).getY(), BigDecimal.valueOf(9));
        assertEquals(reports.get(1).getY(), BigDecimal.valueOf(5));
        assertEquals(reports.get(2).getY(), BigDecimal.valueOf(0));
    }

    @Test
    public void testCalculateMonthlyPriceHistoryReport() {
        populate();
        List<ReportEntryDTO> reports = cottageReportsService.calculateMonthlyPriceHistoryReport(owner,
                LocalDateTime.of(2022, 1, 1, 0, 0), LocalDateTime.of(2022, 3, 3, 0, 0));
        assertThat(reports).hasSize(3);
        assertEquals(reports.get(0).getY(), BigDecimal.valueOf(74));
        assertEquals(reports.get(1).getY(), BigDecimal.valueOf(74));
        assertEquals(reports.get(2).getY(), BigDecimal.valueOf(74));
    }

}
