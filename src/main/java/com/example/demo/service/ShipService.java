package com.example.demo.service;

import com.example.demo.dto.UserFilterDTO;
import com.example.demo.dto.comparators.cottage.*;
import com.example.demo.dto.comparators.ship.*;
import com.example.demo.model.Cottage;
import com.example.demo.model.Reservation;
import com.example.demo.model.Ship;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ShipService {
    private ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public Ship findOne(Integer id) {
        return this.shipRepository.findById(id).orElseGet(null);
    }

    public Ship save(Ship ship) {
        return this.shipRepository.save(ship);
    }

    public void remove(Integer id) {
        shipRepository.deleteById(id);
    }

    public boolean checkReservations(Ship ship) {
        for (Reservation r : ship.getReservations()) {
            if (r.getEnd().compareTo(LocalDateTime.now()) >= 0) {
                return false;
            }
        }
        return true;
    }

    public List<Ship> getShips() {
        return shipRepository.findAll();
    }

    public List<Ship> filter(UserFilterDTO userFilterDTO) {
        List<Ship> retVal = new ArrayList<>();
        List<Ship> all = shipRepository.findAll();
        for(Ship s : all)
        {
            if (isValidDate(s, userFilterDTO) && isValidRate(s,userFilterDTO.getRate()) &&
                    isValidCountryAndCity(s, userFilterDTO) && isValidNumOfPeople(s,userFilterDTO))
            {
                retVal.add(s);
            }
        }
        sortShips(retVal, getStringSortBy(userFilterDTO));
        return retVal;
    }

    private void sortShips(List<Ship> ships, String sortBy) {
        Comparator<Ship> comparator = selectComparator(sortBy);
        if (sortBy.equalsIgnoreCase("rate")) ships.sort(Collections.reverseOrder(comparator));
        else ships.sort(comparator);
    }

    private Comparator<Ship> selectComparator(String sortBy) {
        switch (sortBy) {
            /*case "price":
                return new ShipPriceComparator();*/
            case "rate":
                return new ShipRatingComparator();
            case "city":
                return new ShipCityComparator();
            case "country":
                return new ShipCountryComparator();
            default:
                return new ShipNameComparator();
        }
    }

    private String getStringSortBy(UserFilterDTO userFilterDTO) {
        List<String> sortBy = userFilterDTO.getSortByList();
        int idx = userFilterDTO.getSortBy();
        return sortBy.get(idx).toLowerCase();
    }

    private boolean isValidNumOfPeople(Ship s, UserFilterDTO userFilterDTO) {
        int shipNum = s.getCapacity();
        int requested = userFilterDTO.getNumberOfPeople();
        return shipNum >= requested;
    }

    private boolean isValidCountryAndCity(Ship s, UserFilterDTO userFilterDTO) {
        if (!userFilterDTO.getCountry().equals("") && !userFilterDTO.getCountry().equalsIgnoreCase(s.getAddress().getCountry())) return false;
        // ako je drzava unesena i nije ono sto je napisano tamo
        if (!userFilterDTO.getCity().equals("") && !userFilterDTO.getCity().equalsIgnoreCase(s.getAddress().getCity())) return false;
        return true;
    }

    private boolean isValidRate(Ship s, int rate) {
        return s.getRateOrNegativeOne()>=rate || s.getRateOrNegativeOne()==-1;
    }

    private boolean isValidDate(Ship s, UserFilterDTO userFilterDTO) {
        for(Reservation r : s.getReservations())
        {
            // ako nadjes bar jednu rezervaciju da joj je pcetni ili krajnji datum unutar nase, vrati false
            // ako nadjes bar jednu rezervaciju da joj je pocetni datum prije nase, a krajni poslije, vrati false
            if (r.getReservationStatus()!= ReservationStatus.CANCELLED) {
                // za sve aktivne rezervacije gledamo ima li poklapanje
                if (isInMidDate(r.getStart(), userFilterDTO) || isInMidDate(r.getEnd(), userFilterDTO) || isAround(r, userFilterDTO))
                    return false;
            }
            else {
                // za dve koje nisu aktivne gledamo da li je bilo bas na isti datum da mu to onemogucimo
                if (equalDates(r, userFilterDTO)) return false;
            }
        }
        return true;
    }

    private boolean equalDates(Reservation r, UserFilterDTO userFilterDTO) {
        LocalDateTime r1 = r.getStart();
        LocalDateTime r2 = r.getEnd();
        LocalDateTime u1 = getLocalDatetimeFromVuePicker(userFilterDTO.getFromDate());
        LocalDateTime u2 = getLocalDatetimeFromVuePicker(userFilterDTO.getToDate());
        return r1==u1 && r2==u2;
    }

    private boolean isAround(Reservation r, UserFilterDTO userFilterDTO) {
        LocalDateTime rStart = r.getStart();
        LocalDateTime rEnd = r.getEnd();
        LocalDateTime uStart = getLocalDatetimeFromVuePicker(userFilterDTO.getFromDate());
        LocalDateTime uEnd = getLocalDatetimeFromVuePicker(userFilterDTO.getToDate());
        return rStart.isBefore(uStart) && uEnd.isBefore(rEnd);

    }

    private boolean isInMidDate(LocalDateTime date, UserFilterDTO userFilterDTO) {
        LocalDateTime reservationStart = getLocalDatetimeFromVuePicker(userFilterDTO.getFromDate());
        LocalDateTime reservationEnd = getLocalDatetimeFromVuePicker(userFilterDTO.getToDate());
        return reservationStart.isBefore(date) && date.isBefore(reservationEnd);
    }

    private LocalDateTime getLocalDatetimeFromVuePicker(String d)
    {
        //String sub = d.substring(0, 24);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return LocalDateTime.parse(d, formatter);
    }
}
