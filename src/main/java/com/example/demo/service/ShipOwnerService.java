package com.example.demo.service;

import com.example.demo.dto.FilterShipDTO;
import com.example.demo.dto.ReportEntryDTO;
import com.example.demo.dto.comparators.ship.*;
import com.example.demo.model.Address;
import com.example.demo.model.Ship;
import com.example.demo.model.ShipOwner;
import com.example.demo.repository.ShipOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ShipOwnerService {
    private ShipOwnerRepository shipOwnerRepository;
    private ShipReportsService shipReportsService;

    @Autowired
    public ShipOwnerService(ShipOwnerRepository shipOwnerRepository, ShipReportsService shipReportsService) {
        this.shipOwnerRepository = shipOwnerRepository;
        this.shipReportsService = shipReportsService;
    }

    public ShipOwner findOne(Integer id) {
        return shipOwnerRepository.findById(id).orElseGet(null);
    }

    public ShipOwner save(ShipOwner shipOwner) {
        return this.shipOwnerRepository.save(shipOwner);
    }

    public List<ShipOwner> findAlladmin(){
        return  shipOwnerRepository.findAll();
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

    public List<ReportEntryDTO> calculateIncomeReport(ShipOwner owner, LocalDateTime start, LocalDateTime end, String kind) {
        if (kind.equalsIgnoreCase("Monthly")) {
            return shipReportsService.calculateMonthlyIncome(owner, start, end);
        } else if (kind.equalsIgnoreCase("Weekly")) {
            return shipReportsService.calculateWeeklyIncome(owner, start, end);
        } else if (kind.equalsIgnoreCase("Daily")) {
            return shipReportsService.calculateDailyIncome(owner, start, end);
        } else return shipReportsService.calculateByOfferIncome(owner, start, end);
    }


    public List<ReportEntryDTO> calculateVisitReport(ShipOwner owner, LocalDateTime start, LocalDateTime end, String kind) {
        if (kind.equalsIgnoreCase("Monthly")) {
            return shipReportsService.calculateMonthlyVisitReport(owner, start, end);
        } else if (kind.equalsIgnoreCase("Weekly")) {
            return shipReportsService.calculateWeeklyVisitReport(owner, start, end);
        } else if (kind.equalsIgnoreCase("Daily")) {
            return shipReportsService.calculateDailyVisitReport(owner, start, end);
        } else return shipReportsService.calculateByOfferVisitReport(owner, start, end);
    }


    public List<ReportEntryDTO> calculatePriceHistoryReport(ShipOwner owner, LocalDateTime start, LocalDateTime end, String kind) {
        if (kind.equalsIgnoreCase("Monthly")) {
            return shipReportsService.calculateMonthlyPriceHistoryReport(owner, start, end);
        } else if (kind.equalsIgnoreCase("Weekly")) {
            return shipReportsService.calculateWeeklyPriceHistoryReport(owner, start, end);
        } else if (kind.equalsIgnoreCase("Daily")) {
            return shipReportsService.calculateDailyPriceHistoryReport(owner, start, end);
        } else return shipReportsService.calculateByOfferPriceHistoryReport(owner, start, end);
    }


    private void sortShips(List<Ship> ships, String sortBy, boolean desc) {
        Comparator<Ship> comparator = SelectComparator(sortBy);
        if (desc) ships.sort(Collections.reverseOrder(comparator));
        else ships.sort(comparator);
    }

    private Comparator<Ship> SelectComparator(String sortBy) {
        switch (sortBy) {
            case "price":
                return new ShipPriceComparator();
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
            case "length":
                return new ShipLengthComparator();
            default:
                return new ShipNameComparator();
        }
    }
}
