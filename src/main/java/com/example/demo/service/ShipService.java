package com.example.demo.service;

import com.example.demo.dto.UserFilterDTO;
import com.example.demo.dto.comparators.ship.ShipCityComparator;
import com.example.demo.dto.comparators.ship.ShipCountryComparator;
import com.example.demo.dto.comparators.ship.ShipNameComparator;
import com.example.demo.dto.comparators.ship.ShipRatingComparator;
import com.example.demo.model.Client;
import com.example.demo.model.Cottage;
import com.example.demo.model.Reservation;
import com.example.demo.model.Ship;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.repository.ShipRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ShipService {
    private ShipRepository shipRepository;
    private EmailSender emailSender;

    @Autowired
    public ShipService(ShipRepository shipRepository, EmailSender emailSender)
    {
        this.shipRepository = shipRepository;
        this.emailSender = emailSender;
    }

    @Transactional
    @Cacheable("ship")
    public Ship findOne(Integer id) {
        return this.shipRepository.findById(id).orElseGet(null);
    }

    @Transactional
    @CachePut(cacheNames = "ship", key="#ship.id")
    public Ship save(Ship ship) {
        return this.shipRepository.save(ship);
    }

    @Transactional
    @CacheEvict(cacheNames = "cottage", key="#id")
    public void remove(Integer id) {
        shipRepository.deleteById(id);
    }

    @Transactional
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
        for (Ship s : all) {
            if (isValidDate(s, userFilterDTO) && isValidRate(s, userFilterDTO) &&
                    isValidCountryAndCity(s, userFilterDTO) && isValidNumOfPeople(s, userFilterDTO)) {
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
        if (!userFilterDTO.getCountry().equals("") && !userFilterDTO.getCountry().equalsIgnoreCase(s.getAddress().getCountry()))
            return false;
        // ako je drzava unesena i nije ono sto je napisano tamo
        if (!userFilterDTO.getCity().equals("") && !userFilterDTO.getCity().equalsIgnoreCase(s.getAddress().getCity()))
            return false;
        return true;
    }

    private boolean isValidRate(Ship s, UserFilterDTO u) {
        if (u.getRate()!=0)
        {
            return s.getRateOrNegativeOne() >= u.getRate() || s.getRateOrNegativeOne() == -1;
        }
        return true;
    }

    private boolean isValidDate(Ship s, UserFilterDTO userFilterDTO) {
        if (userFilterDTO.getFromDate()!=null || userFilterDTO.getToDate()!=null)
        {
            for (Reservation r : s.getReservations()) {
                // ako nadjes bar jednu rezervaciju da joj je pcetni ili krajnji datum unutar nase, vrati false
                // ako nadjes bar jednu rezervaciju da joj je pocetni datum prije nase, a krajni poslije, vrati false
                if (r.getReservationStatus() != ReservationStatus.CANCELLED) {
                    // za sve aktivne rezervacije gledamo ima li poklapanje
                    if (isInMidDate(r.getStart(), userFilterDTO) || isInMidDate(r.getEnd(), userFilterDTO) || isAround(r, userFilterDTO))
                        return false;
                } else {
                    // za dve koje nisu aktivne gledamo da li je bilo bas na isti datum da mu to onemogucimo
                    if (equalDates(r, userFilterDTO)) return false;
                }
            }
            return true;
        }
        return true;

    }

    private boolean equalDates(Reservation r, UserFilterDTO userFilterDTO) {
        LocalDateTime r1 = r.getStart();
        LocalDateTime r2 = r.getEnd();
        LocalDateTime u1 = getLocalDatetimeFromVuePicker(userFilterDTO.getFromDate());
        LocalDateTime u2 = getLocalDatetimeFromVuePicker(userFilterDTO.getToDate());
        return r1 == u1 && r2 == u2;
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

    private LocalDateTime getLocalDatetimeFromVuePicker(String d) {
        //String sub = d.substring(0, 24);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return LocalDateTime.parse(d, formatter);
    }

    public void notifySubscribers(Ship ship) {
        String title = "Subscription update";
        String content = "There is a new fast reservation available for " + ship.getName();
        for (Client s : ship.getSubscribers()) {
            emailSender.send(s.getEmail(), title, content);
        }
    }
}
