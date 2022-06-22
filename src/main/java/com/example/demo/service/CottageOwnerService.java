package com.example.demo.service;

import com.example.demo.dto.FilterCottageDTO;
import com.example.demo.dto.ReportEntryDTO;
import com.example.demo.dto.comparators.cottage.*;
import com.example.demo.model.Address;
import com.example.demo.model.Cottage;
import com.example.demo.model.CottageOwner;
import com.example.demo.repository.CottageOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CottageOwnerService {
    private CottageOwnerRepository cottageOwnerRepository;
    private CottageReportsService cottageReportsService;

    @Autowired
    public CottageOwnerService(CottageOwnerRepository cottageOwnerRepository, CottageReportsService cottageReportsService) {
        this.cottageOwnerRepository = cottageOwnerRepository;
        this.cottageReportsService = cottageReportsService;
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

    public List<ReportEntryDTO> calculateIncomeReport(CottageOwner owner, LocalDateTime start, LocalDateTime end, String kind) {
        if (kind.equalsIgnoreCase("Monthly")) {
            return cottageReportsService.calculateMonthlyIncome(owner, start, end);
        } else if (kind.equalsIgnoreCase("Weekly")) {
            return cottageReportsService.calculateWeeklyIncome(owner, start, end);
        } else if (kind.equalsIgnoreCase("Daily")) {
            return cottageReportsService.calculateDailyIncome(owner, start, end);
        } else return cottageReportsService.calculateByOfferIncome(owner, start, end);
    }

    public List<ReportEntryDTO> calculateVisitReport(CottageOwner owner, LocalDateTime start, LocalDateTime end, String kind) {
        if (kind.equalsIgnoreCase("Monthly")) {
            return cottageReportsService.calculateMonthlyVisitReport(owner, start, end);
        } else if (kind.equalsIgnoreCase("Weekly")) {
            return cottageReportsService.calculateWeeklyVisitReport(owner, start, end);
        } else if (kind.equalsIgnoreCase("Daily")) {
            return cottageReportsService.calculateDailyVisitReport(owner, start, end);
        } else return cottageReportsService.calculateByOfferVisitReport(owner, start, end);
    }

    public List<ReportEntryDTO> calculatePriceHistoryReport(CottageOwner owner, LocalDateTime start, LocalDateTime end, String kind) {
        if (kind.equalsIgnoreCase("Monthly")) {
            return cottageReportsService.calculateMonthlyPriceHistoryReport(owner, start, end);
        } else if (kind.equalsIgnoreCase("Weekly")) {
            return cottageReportsService.calculateWeeklyPriceHistoryReport(owner, start, end);
        } else if (kind.equalsIgnoreCase("Daily")) {
            return cottageReportsService.calculateDailyPriceHistoryReport(owner, start, end);
        } else return cottageReportsService.calculateByOfferPriceHistoryReport(owner, start, end);
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
