package com.example.demo.service;

import com.example.demo.dto.FilterShipDTO;
import com.example.demo.dto.IncomeReportDTO;
import com.example.demo.dto.comparators.ship.*;
import com.example.demo.model.*;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.repository.ShipOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
