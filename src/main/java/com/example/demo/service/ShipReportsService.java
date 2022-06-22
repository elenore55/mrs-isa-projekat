package com.example.demo.service;

import com.example.demo.dto.ReportEntryDTO;
import com.example.demo.dto.comparators.ReportEntryDaysComparator;
import com.example.demo.model.*;
import com.example.demo.model.enums.ReservationStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShipReportsService {

    public List<ReportEntryDTO> calculateByOfferIncome(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, ReportEntryDTO> result = new HashMap<>();
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            boolean isFast = false;
            Ship c = (Ship) r.getOffer();
            ReportEntryDTO dto;
            if (result.containsKey(c.getName())) dto = result.get(c.getName());
            else dto = new ReportEntryDTO(c.getName(), BigDecimal.valueOf(0));
            for (FastReservation fr : c.getFastReservations()) {
                if (fr.getStart().equals(r.getStart()) && fr.getEnd().equals(r.getEnd())) {
                    isFast = true;
                    dto.setY(dto.getY().add(fr.getPrice()));
                    break;
                }
            }
            if (!isFast) {
                BigDecimal newIncome = c.getPriceList().multiply(r.getDuration());
                dto.setY(dto.getY().add(newIncome));
            }
            result.put(c.getName(), dto);
        }
        return new ArrayList<>(result.values());
    }

    public List<ReportEntryDTO> calculateMonthlyIncome(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, BigDecimal> incomePerMonth = new HashMap<>();
        Month month = start.getMonth();
        int year = start.getYear();
        start = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime startCopy = start;
        while (true) {
            String monthStr = startCopy.getMonth().toString() + " " + startCopy.getYear();
            incomePerMonth.put(monthStr, BigDecimal.valueOf(0));
            LocalDateTime next = startCopy.plusMonths(1);
            if (next.compareTo(end) >= 0) break;
            startCopy = next;
        }
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            boolean isFast = false;
            String monthStr = r.getStart().getMonth().toString() + " " + r.getStart().getYear();
            BigDecimal value = incomePerMonth.get(monthStr);
            Ship c = (Ship) r.getOffer();
            for (FastReservation fr : c.getFastReservations()) {
                if (fr.getStart().equals(r.getStart()) && fr.getEnd().equals(r.getEnd())) {
                    isFast = true;
                    value = value.add(fr.getPrice());
                    break;
                }
            }
            if (!isFast) {
                BigDecimal newIncome = c.getPriceList().multiply(r.getDuration());
                value = value.add(newIncome);
            }
            incomePerMonth.put(monthStr, value);
        }
        List<ReportEntryDTO> result = new ArrayList<>();
        for (String m : incomePerMonth.keySet()) result.add(new ReportEntryDTO(m, incomePerMonth.get(m)));
        result.sort(new ReportEntryDaysComparator());
        return result;
    }

    public List<ReportEntryDTO> calculateWeeklyIncome(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, BigDecimal> incomePerWeek = new HashMap<>();
        DayOfWeek dayOfWeek = start.getDayOfWeek();
        start = start.minusDays(dayOfWeek.getValue() - 1);
        LocalDateTime startCopy = start;
        while (true) {
            String weekStr = startCopy.getDayOfMonth() + " " + startCopy.getMonth().toString() + " " + startCopy.getYear();
            incomePerWeek.put(weekStr, BigDecimal.valueOf(0));
            LocalDateTime next = startCopy.plusDays(7);
            if (next.compareTo(end) >= 0) break;
            startCopy = next;
        }
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            boolean isFast = false;

            Ship c = (Ship) r.getOffer();
            for (FastReservation fr : c.getFastReservations()) {
                if (fr.getStart().equals(r.getStart()) && fr.getEnd().equals(r.getEnd())) {
                    isFast = true;
                    break;
                }
            }
            if (!isFast) {
                LocalDateTime resStart = r.getStart();
                while (resStart.compareTo(r.getEnd()) < 0 && resStart.compareTo(end) < 0) {
                    LocalDateTime startOfWeek = resStart.minusDays(resStart.getDayOfWeek().getValue() - 1);
                    String weekStr = startOfWeek.getDayOfMonth() + " " + startOfWeek.getMonth().toString() + " " + startOfWeek.getYear();
                    resStart = resStart.plusDays(1);
                    incomePerWeek.put(weekStr, incomePerWeek.get(weekStr).add(c.getPriceList()));
                }
            }
        }
        List<ReportEntryDTO> result = new ArrayList<>();
        for (String m : incomePerWeek.keySet()) result.add(new ReportEntryDTO(m, incomePerWeek.get(m)));
        result.sort(new ReportEntryDaysComparator());
        return result;
    }

    public List<ReportEntryDTO> calculateDailyIncome(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, BigDecimal> incomePerDay = new HashMap<>();
        LocalDateTime startCopy = start;
        while (true) {
            String dayStr = startCopy.getDayOfMonth() + " " + startCopy.getMonth().toString() + " " + startCopy.getYear();
            incomePerDay.put(dayStr, BigDecimal.valueOf(0));
            LocalDateTime next = startCopy.plusDays(1);
            if (next.compareTo(end) >= 0) break;
            startCopy = next;
        }
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            boolean isFast = false;

            Ship c = (Ship) r.getOffer();
            for (FastReservation fr : c.getFastReservations()) {
                if (fr.getStart().equals(r.getStart()) && fr.getEnd().equals(r.getEnd())) {
                    isFast = true;
                    break;
                }
            }
            if (!isFast) {
                LocalDateTime resStart = r.getStart();
                while (resStart.compareTo(r.getEnd()) < 0 && resStart.compareTo(end) < 0) {
                    String dayStr = resStart.getDayOfMonth() + " " + resStart.getMonth().toString() + " " + resStart.getYear();
                    if (incomePerDay.containsKey(dayStr)) {
                        incomePerDay.put(dayStr, incomePerDay.get(dayStr).add(c.getPriceList()));
                    }
                    resStart = resStart.plusDays(1);
                }
            }
        }
        List<ReportEntryDTO> result = new ArrayList<>();
        for (String m : incomePerDay.keySet()) result.add(new ReportEntryDTO(m, incomePerDay.get(m)));
        result.sort(new ReportEntryDaysComparator());
        return result;
    }

    public List<ReportEntryDTO> calculateByOfferVisitReport(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, ReportEntryDTO> result = new HashMap<>();
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            Ship c = (Ship) r.getOffer();
            ReportEntryDTO dto;
            if (result.containsKey(c.getName())) dto = result.get(c.getName());
            else dto = new ReportEntryDTO(c.getName(), BigDecimal.valueOf(0));
            long days = ChronoUnit.DAYS.between(r.getStart(), r.getEnd());
            dto.setY(dto.getY().add(BigDecimal.valueOf(days)));
            result.put(c.getName(), dto);
        }
        return new ArrayList<>(result.values());
    }

    public List<ReportEntryDTO> calculateMonthlyVisitReport(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, Integer> visitsPerMonth = new HashMap<>();
        Month month = start.getMonth();
        int year = start.getYear();
        start = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime startCopy = start;
        while (true) {
            String monthStr = startCopy.getMonth().toString() + " " + startCopy.getYear();
            visitsPerMonth.put(monthStr, 0);
            LocalDateTime next = startCopy.plusMonths(1);
            if (next.compareTo(end) >= 0) break;
            startCopy = next;
        }
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            String monthStr = r.getStart().getMonth().toString() + " " + r.getStart().getYear();
            visitsPerMonth.put(monthStr, (int) (visitsPerMonth.get(monthStr) + ChronoUnit.DAYS.between(r.getStart(), r.getEnd())));
        }
        List<ReportEntryDTO> result = new ArrayList<>();
        for (String m : visitsPerMonth.keySet())
            result.add(new ReportEntryDTO(m, BigDecimal.valueOf(visitsPerMonth.get(m))));
        result.sort(new ReportEntryDaysComparator());
        return result;
    }

    public List<ReportEntryDTO> calculateWeeklyVisitReport(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, Integer> visitsPerWeek = new HashMap<>();
        DayOfWeek dayOfWeek = start.getDayOfWeek();
        start = start.minusDays(dayOfWeek.getValue() - 1);
        LocalDateTime startCopy = start;
        while (true) {
            String weekStr = startCopy.getDayOfMonth() + " " + startCopy.getMonth().toString() + " " + startCopy.getYear();
            visitsPerWeek.put(weekStr, 0);
            LocalDateTime next = startCopy.plusDays(7);
            if (next.compareTo(end) >= 0) break;
            startCopy = next;
        }
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            LocalDateTime resStart = r.getStart();
            while (resStart.compareTo(r.getEnd()) < 0 && resStart.compareTo(end) < 0) {
                LocalDateTime startOfWeek = resStart.minusDays(resStart.getDayOfWeek().getValue() - 1);
                String weekStr = startOfWeek.getDayOfMonth() + " " + startOfWeek.getMonth().toString() + " " + startOfWeek.getYear();
                visitsPerWeek.put(weekStr, visitsPerWeek.get(weekStr) + 1);
                resStart = resStart.plusDays(1);
            }
        }
        List<ReportEntryDTO> result = new ArrayList<>();
        for (String m : visitsPerWeek.keySet())
            result.add(new ReportEntryDTO(m, BigDecimal.valueOf(visitsPerWeek.get(m))));
        result.sort(new ReportEntryDaysComparator());
        return result;
    }

    public List<ReportEntryDTO> calculateDailyVisitReport(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, Integer> visitsPerDay = new HashMap<>();
        LocalDateTime startCopy = start;
        while (true) {
            String dayStr = startCopy.getDayOfMonth() + " " + startCopy.getMonth().toString() + " " + startCopy.getYear();
            visitsPerDay.put(dayStr, 0);
            LocalDateTime next = startCopy.plusDays(1);
            if (next.compareTo(end) >= 0) break;
            startCopy = next;
        }
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            LocalDateTime resStart = r.getStart();
            while (resStart.compareTo(r.getEnd()) < 0 && resStart.compareTo(end) < 0) {
                String dayStr = resStart.getDayOfMonth() + " " + resStart.getMonth().toString() + " " + resStart.getYear();
                visitsPerDay.put(dayStr, visitsPerDay.get(dayStr) + 1);
                resStart = resStart.plusDays(1);
            }
        }
        List<ReportEntryDTO> result = new ArrayList<>();
        for (String m : visitsPerDay.keySet())
            result.add(new ReportEntryDTO(m, BigDecimal.valueOf(visitsPerDay.get(m))));
        result.sort(new ReportEntryDaysComparator());
        return result;
    }

    public List<ReportEntryDTO> calculateByOfferPriceHistoryReport(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, List<BigDecimal>> prices = new HashMap<>();
        for (Ship c : owner.getShips()) {
            List<PriceList> priceHistory = c.getPriceHistory();
            List<BigDecimal> temp = new ArrayList<>();
            for (int i = 0; i < priceHistory.size(); i++) {
                PriceList pr = priceHistory.get(i);
                LocalDate prStart = pr.getStartDate();
                LocalDate prEnd;
                if (i == priceHistory.size() - 1) prEnd = LocalDate.now();
                else prEnd = priceHistory.get(i + 1).getStartDate();
                if (prStart.compareTo(ChronoLocalDate.from(end)) >= 0) break;
                if (prEnd.compareTo(ChronoLocalDate.from(start)) < 0) continue;
                while (prStart.compareTo(ChronoLocalDate.from(start)) < 0) prStart = prStart.plusDays(1);
                while (prStart.compareTo(prEnd) < 0 && prStart.compareTo(ChronoLocalDate.from(end)) < 0) {
                    temp.add(pr.getAmount());
                    prStart = prStart.plusDays(1);
                }
            }
            prices.put(c.getName(), temp);
        }
        List<ReportEntryDTO> result = new ArrayList<>();
        for (String shipName : prices.keySet()) {
            BigDecimal sum = prices.get(shipName).stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            if (sum.equals(BigDecimal.valueOf(0))) result.add(new ReportEntryDTO(shipName, BigDecimal.valueOf(0)));
            else {
                BigDecimal avg = sum.divide(BigDecimal.valueOf(prices.get(shipName).size()), RoundingMode.CEILING);
                result.add(new ReportEntryDTO(shipName, avg));
            }
        }
        return result;
    }

    public List<ReportEntryDTO> calculateMonthlyPriceHistoryReport(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        List<Ship> ships = owner.getShips();
        Month month = start.getMonth();
        int year = start.getYear();
        start = LocalDateTime.of(year, month, 1, 0, 0);

        Map<String, List<BigDecimal>> amountsPerMonth = new HashMap<>();
        while (true) {
            String monthStr = start.getMonth().toString() + " " + start.getYear();
            amountsPerMonth.put(monthStr, new ArrayList<>());
            LocalDateTime next = start.plusMonths(1);
            if (next.compareTo(end) >= 0) break;
            start = next;
        }

        List<ReportEntryDTO> result = new ArrayList<>();

        for (Ship c : ships) {
            List<PriceList> history = c.getPriceHistory();
            for (int i = 0; i < history.size(); i++) {
                LocalDate priceStart = history.get(i).getStartDate();
                LocalDate priceEnd;
                if (i == history.size() - 1) priceEnd = LocalDate.now();
                else priceEnd = history.get(i + 1).getStartDate();
                while (priceStart.compareTo(priceEnd) < 0) {
                    String priceMonthStr = priceStart.getMonth().toString() + " " + priceStart.getYear();
                    if (!amountsPerMonth.containsKey(priceMonthStr)) {
                        priceStart = priceStart.plusDays(1);
                        continue;
                    }
                    List<BigDecimal> amounts = amountsPerMonth.get(priceMonthStr);
                    amounts.add(history.get(i).getAmount());
                    amountsPerMonth.put(priceMonthStr, amounts);
                    priceStart = priceStart.plusDays(1);
                }
            }
        }

        for (String m : amountsPerMonth.keySet()) {
            List<BigDecimal> prices = amountsPerMonth.get(m);
            BigDecimal sum = prices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            if (sum.equals(BigDecimal.valueOf(0))) result.add(new ReportEntryDTO(m, BigDecimal.valueOf(0)));
            else {
                BigDecimal avg = sum.divide(BigDecimal.valueOf(prices.size()), 2, RoundingMode.CEILING);
                result.add(new ReportEntryDTO(m, avg));
            }
        }
        result.sort(new ReportEntryDaysComparator());
        return result;
    }

    public List<ReportEntryDTO> calculateDailyPriceHistoryReport(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        List<Ship> ships = owner.getShips();

        Map<String, List<BigDecimal>> amountsPerDay = new HashMap<>();
        while (true) {
            String dayStr = start.getDayOfMonth() + " " + start.getMonth().toString() + " " + start.getYear();
            amountsPerDay.put(dayStr, new ArrayList<>());
            LocalDateTime next = start.plusDays(1);
            if (next.compareTo(end) >= 0) break;
            start = next;
        }

        List<ReportEntryDTO> result = new ArrayList<>();

        for (Ship c : ships) {
            List<PriceList> history = c.getPriceHistory();
            for (int i = 0; i < history.size(); i++) {
                LocalDate priceStart = history.get(i).getStartDate();
                LocalDate priceEnd;
                if (i == history.size() - 1) priceEnd = LocalDate.now();
                else priceEnd = history.get(i + 1).getStartDate();

                while (priceStart.compareTo(priceEnd) < 0) {
                    String priceDayStr = priceStart.getDayOfMonth() + " " + priceStart.getMonth().toString() + " " + priceStart.getYear();
                    if (!amountsPerDay.containsKey(priceDayStr)) {
                        priceStart = priceStart.plusDays(1);
                        continue;
                    }
                    List<BigDecimal> amounts = amountsPerDay.get(priceDayStr);
                    amounts.add(history.get(i).getAmount());
                    amountsPerDay.put(priceDayStr, amounts);
                    priceStart = priceStart.plusDays(1);
                }
            }
        }

        for (String m : amountsPerDay.keySet()) {
            List<BigDecimal> prices = amountsPerDay.get(m);
            BigDecimal sum = prices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            if (sum.equals(BigDecimal.valueOf(0))) result.add(new ReportEntryDTO(m, BigDecimal.valueOf(0)));
            else {
                BigDecimal avg = sum.divide(BigDecimal.valueOf(prices.size()), 2, RoundingMode.CEILING);
                result.add(new ReportEntryDTO(m, avg));
            }
        }
        result.sort(new ReportEntryDaysComparator());
        return result;
    }

    public List<ReportEntryDTO> calculateWeeklyPriceHistoryReport(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        List<Ship> ships = owner.getShips();
        DayOfWeek dayOfWeek = start.getDayOfWeek();
        start = start.minusDays(dayOfWeek.getValue() - 1);

        Map<String, List<BigDecimal>> amountsPerWeek = new HashMap<>();
        while (true) {
            String weekStr = start.getDayOfMonth() + " " + start.getMonth().toString() + " " + start.getYear();
            amountsPerWeek.put(weekStr, new ArrayList<>());
            LocalDateTime next = start.plusDays(7);
            if (next.compareTo(end) >= 0) break;
            start = next;
        }

        List<ReportEntryDTO> result = new ArrayList<>();

        for (Ship c : ships) {
            List<PriceList> history = c.getPriceHistory();
            for (int i = 0; i < history.size(); i++) {
                LocalDate priceStart = history.get(i).getStartDate();
                LocalDate priceEnd;
                if (i == history.size() - 1) priceEnd = LocalDate.now();
                else priceEnd = history.get(i + 1).getStartDate();

                while (priceStart.compareTo(priceEnd) < 0) {
                    LocalDate startOfWeek = priceStart.minusDays(priceStart.getDayOfWeek().getValue() - 1);
                    String priceWeekStr = startOfWeek.getDayOfMonth() + " " + startOfWeek.getMonth().toString() + " " + startOfWeek.getYear();
                    if (!amountsPerWeek.containsKey(priceWeekStr)) {
                        priceStart = priceStart.plusDays(1);
                        continue;
                    }
                    List<BigDecimal> amounts = amountsPerWeek.get(priceWeekStr);
                    amounts.add(history.get(i).getAmount());
                    amountsPerWeek.put(priceWeekStr, amounts);
                    priceStart = priceStart.plusDays(1);
                }
            }
        }

        for (String m : amountsPerWeek.keySet()) {
            List<BigDecimal> prices = amountsPerWeek.get(m);
            BigDecimal sum = prices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            if (sum.equals(BigDecimal.valueOf(0))) result.add(new ReportEntryDTO(m, BigDecimal.valueOf(0)));
            else {
                BigDecimal avg = sum.divide(BigDecimal.valueOf(prices.size()), 2, RoundingMode.CEILING);
                result.add(new ReportEntryDTO(m, avg));
            }
        }
        result.sort(new ReportEntryDaysComparator());
        return result;
    }
}
