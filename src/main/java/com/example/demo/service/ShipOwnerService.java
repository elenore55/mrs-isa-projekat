package com.example.demo.service;

import com.example.demo.dto.FilterShipDTO;
import com.example.demo.dto.IncomeReportDTO;
import com.example.demo.dto.ReportEntryDTO;
import com.example.demo.dto.VisitReportDTO;
import com.example.demo.dto.comparators.ReportEntryDaysComparator;
import com.example.demo.dto.comparators.ship.*;
import com.example.demo.model.*;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.repository.ShipOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ShipOwnerService {
    private ShipOwnerRepository shipOwnerRepository;

    @Autowired
    public ShipOwnerService(ShipOwnerRepository shipOwnerRepository) {
        this.shipOwnerRepository = shipOwnerRepository;
    }

    public ShipOwner findOne(Integer id) {
        return shipOwnerRepository.findById(id).orElseGet(null);
    }

    public ShipOwner save(ShipOwner shipOwner) {
        return this.shipOwnerRepository.save(shipOwner);
    }

    public List<Ship> searchShips(Integer id, String search) {
        ShipOwner owner = findOne(id);
        List<Ship> ships = owner.getShips();
        List<Ship> result = new ArrayList<>();
        for (Ship s : ships) {
            Address a = s.getAddress();
            if (s.getShipType().toString().toLowerCase().contains(search) || s.getName().toLowerCase().contains(search) || s.getDescription().toLowerCase().contains(search) ||
                    s.getAdditionalInfo().toLowerCase().contains(search) || a.getStreet().toLowerCase().contains(search) || a.getCity().toLowerCase().contains(search) ||
                    a.getCountry().toLowerCase().contains(search)) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Ship> filterShips(Integer id, FilterShipDTO filter) {
        ShipOwner owner = findOne(id);
        List<Ship> ships = owner.getShips();
        List<Ship> result = new ArrayList<>();
        for (Ship s : ships) {
            Address a = s.getAddress();
            if (filter.checkCity(a.getCity()) && filter.checkCountry(a.getCountry()) && filter.checkPrice(s.getPriceList()) &&
                    filter.checkLength(s.getLength()) && filter.checkCapacity(s.getCapacity()) && filter.checkSpeed(s.getMaxSpeed())) {
                result.add(s);
            }
        }
        sortShips(result, filter.getSortParam().toLowerCase(), filter.getSortDir().equalsIgnoreCase("descending"));
        return result;
    }

    public List<IncomeReportDTO> calculateIncome(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, IncomeReportDTO> result = new HashMap<>();
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            boolean isFast = false;
            Ship s = (Ship) r.getOffer();
            IncomeReportDTO dto;
            if (result.containsKey(s.getName())) dto = result.get(s.getName());
            else dto = new IncomeReportDTO(s.getId(), s.getName(), BigDecimal.valueOf(0));

            for (FastReservation fr : s.getFastReservations()) {
                if (fr.getStart().equals(r.getStart()) && fr.getEnd().equals(r.getEnd())) {
                    isFast = true;
                    dto.setIncome(dto.getIncome().add(fr.getPrice()));
                    break;
                }
            }
            if (!isFast) {
                BigDecimal newIncome = s.getPriceList().multiply(r.getDuration());
                dto.setIncome(dto.getIncome().add(newIncome));
            }
            result.put(s.getName(), dto);
        }
        return new ArrayList<>(result.values());
    }

    public List<VisitReportDTO> calculateVisitReport(ShipOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, VisitReportDTO> result = new HashMap<>();
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            Ship s = (Ship) r.getOffer();
            VisitReportDTO dto;
            if (result.containsKey(s.getName())) dto = result.get(s.getName());
            else dto = new VisitReportDTO(s.getId(), s.getName(), (long) 0);
            long days = ChronoUnit.DAYS.between(r.getStart(), r.getEnd());
            dto.setDaysVisited(dto.getDaysVisited() + days);
            result.put(s.getName(), dto);
        }
        return new ArrayList<>(result.values());
    }

    public List<ReportEntryDTO> calculatePriceHistoryReport(ShipOwner owner, LocalDateTime start, LocalDateTime end, String kind) {
        if (kind.equalsIgnoreCase("Monthly")) {
            return calculateMonthlyPriceHistoryReport(owner, start, end);
        } else if (kind.equalsIgnoreCase("Weekly")) {
            return calculateWeeklyPriceHistoryReport(owner, start, end);
        } else return calculateDailyPriceHistoryReport(owner, start, end);
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

    private void sortShips(List<Ship> ships, String sortBy, boolean desc) {
        Comparator<Ship> comparator = SelectComparator(sortBy);
        if (desc) ships.sort(Collections.reverseOrder(comparator));
        else ships.sort(comparator);
    }

    private Comparator<Ship> SelectComparator(String sortBy) {
        switch (sortBy) {
            case "price":
                return new ShipLengthComparator();
            case "capacity":
                return new ShipCapacityComparator();
            case "maximum speed":
                return new ShipSpeedComparator();
            case "type":
                return new ShipTypeComparator();
            case "city":
                return new ShipCityComparator();
            case "country":
                return new ShipCountryComparator();
            case "rating":
                return new ShipRatingComparator();
            default:
                return new ShipNameComparator();
        }
    }
}
