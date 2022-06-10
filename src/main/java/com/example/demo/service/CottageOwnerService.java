package com.example.demo.service;

import com.example.demo.dto.FilterCottageDTO;
import com.example.demo.dto.IncomeReportDTO;
import com.example.demo.dto.ReportEntryDTO;
import com.example.demo.dto.VisitReportDTO;
import com.example.demo.dto.comparators.cottage.*;
import com.example.demo.model.*;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.repository.CottageOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class CottageOwnerService {
    private CottageOwnerRepository cottageOwnerRepository;

    @Autowired
    public CottageOwnerService(CottageOwnerRepository cottageOwnerRepository) {
        this.cottageOwnerRepository = cottageOwnerRepository;
    }

    public CottageOwner findOne(Integer id) {
        return cottageOwnerRepository.findById(id).orElseGet(null);
    }

    public CottageOwner save(CottageOwner owner) {
        return this.cottageOwnerRepository.save(owner);
    }

    public List<Cottage> searchCottages(Integer id, String search) {
        CottageOwner owner = findOne(id);
        List<Cottage> cottages = owner.getCottages();
        List<Cottage> result = new ArrayList<>();
        for (Cottage c : cottages) {
            Address a = c.getAddress();
            if (c.getName().toLowerCase().contains(search) || c.getDescription().toLowerCase().contains(search) || c.getAdditionalInfo().toLowerCase().contains(search) ||
                    a.getStreet().toLowerCase().contains(search) || a.getCity().toLowerCase().contains(search) || a.getCountry().toLowerCase().contains(search)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Cottage> filterCottages(Integer id, FilterCottageDTO filter) {
        CottageOwner owner = findOne(id);
        List<Cottage> cottages = owner.getCottages();
        List<Cottage> result = new ArrayList<>();
        for (Cottage c : cottages) {
            Address a = c.getAddress();
            if (filter.checkCity(a.getCity()) && filter.checkCountry(a.getCountry()) && filter.checkPrice(c.getPriceList())) {
                result.add(c);
            }
        }
        sortCottages(result, filter.getSortParam().toLowerCase(), filter.getSortDir().equalsIgnoreCase("descending"));
        return result;
    }

    public List<IncomeReportDTO> calculateIncome(CottageOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, IncomeReportDTO> result = new HashMap<>();
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            boolean isFast = false;
            Cottage c = (Cottage) r.getOffer();
            IncomeReportDTO dto;
            if (result.containsKey(c.getName())) dto = result.get(c.getName());
            else dto = new IncomeReportDTO(c.getId(), c.getName(), BigDecimal.valueOf(0));

            for (FastReservation fr : c.getFastReservations()) {
                if (fr.getStart().equals(r.getStart()) && fr.getEnd().equals(r.getEnd())) {
                    isFast = true;
                    dto.setIncome(dto.getIncome().add(fr.getPrice()));
                    break;
                }
            }
            if (!isFast) {
                BigDecimal newIncome = c.getPriceList().multiply(r.getDuration());
                dto.setIncome(dto.getIncome().add(newIncome));
            }
            result.put(c.getName(), dto);
        }
        return new ArrayList<>(result.values());
    }

    public List<VisitReportDTO> calculateVisitReport(CottageOwner owner, LocalDateTime start, LocalDateTime end) {
        Map<String, VisitReportDTO> result = new HashMap<>();
        for (Reservation r : owner.getReservations()) {
            if (r.getStart().compareTo(start) < 0 || r.getStart().compareTo(end) > 0) continue;
            if (r.getReservationStatus() == ReservationStatus.CANCELLED || r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED)
                continue;
            Cottage c = (Cottage) r.getOffer();
            VisitReportDTO dto;
            if (result.containsKey(c.getName())) dto = result.get(c.getName());
            else dto = new VisitReportDTO(c.getId(), c.getName(), (long) 0);
            long days = ChronoUnit.DAYS.between(r.getStart(), r.getEnd());
            dto.setDaysVisited(dto.getDaysVisited() + days);
            result.put(c.getName(), dto);
        }
        return new ArrayList<>(result.values());
    }

    public List<ReportEntryDTO> calculateMonthlyPriceHistoryReport(CottageOwner owner, LocalDateTime start, LocalDateTime end) {
        List<Cottage> cottages = owner.getCottages();
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

        for (Cottage c : cottages) {
            List<PriceList> history = c.getPriceHistory();
            for (int i = 0; i < history.size(); i++) {
                LocalDate priceStart = history.get(i).getStartDate();
                LocalDate priceEnd;
                if (i == history.size() - 1) priceEnd = LocalDate.now();
                else priceEnd = history.get(i + 1).getStartDate();
                while (priceStart.compareTo(priceEnd) < 0) {
                    String priceMonthStr = priceStart.getMonth().toString() + " " + priceStart.getYear();
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
        return result;
    }

    private void sortCottages(List<Cottage> cottages, String sortBy, boolean desc) {
        Comparator<Cottage> comparator = selectComparator(sortBy);
        if (desc) cottages.sort(Collections.reverseOrder(comparator));
        else cottages.sort(comparator);
    }

    private Comparator<Cottage> selectComparator(String sortBy) {
        switch (sortBy) {
            case "price":
                return new CottagePriceComparator();
            case "number of rooms":
                return new CottageRoomsComparator();
            case "rating":
                return new CottageRatingComparator();
            case "city":
                return new CottageCityComparator();
            case "country":
                return new CottageCountryComparator();
            default:
                return new CottageNameComparator();
        }
    }
}
