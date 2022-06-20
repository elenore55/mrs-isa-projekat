package com.example.demo.service;

import com.example.demo.dto.UserFilterDTO;
import com.example.demo.dto.comparators.cottage.*;
import com.example.demo.model.Client;
import com.example.demo.model.Cottage;
import com.example.demo.model.Reservation;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.repository.CottageRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CottageService {

    private final CottageRepository cottageRepository;
    private EmailSender emailSender;

    @Autowired
    public CottageService(CottageRepository cottageRepository, EmailSender emailSender) {
        this.cottageRepository = cottageRepository;
        this.emailSender = emailSender;
    }

    public Cottage save(Cottage cottage) {
        return cottageRepository.save(cottage);
    }

    @Transactional
    public List<Cottage> getCottages()
    {
        return cottageRepository.findAll();
    }

    @Transactional
    public Cottage findOne(Integer id) {
        return cottageRepository.findById(id).orElseGet(null);
    }

    @Transactional
    public void remove(Integer id) {
        cottageRepository.deleteById(id);
    }

    @Transactional
    public boolean checkReservations(Cottage cottage) {
        for (Reservation r : cottage.getReservations()) {
            if (r.getEnd().compareTo(LocalDateTime.now()) >= 0) {
                return false;
            }
        }
        return true;
    }

    public List<Cottage> filter(UserFilterDTO userFilterDTO) {
        List<Cottage> retVal = new ArrayList<>();
        List<Cottage> all = cottageRepository.findAll();
        for(Cottage c : all)
        {
            if (isValidDate(c, userFilterDTO) && isValidRate(c,userFilterDTO.getRate()) &&
            isValidCountryAndCity(c, userFilterDTO) && isValidNumOfPeople(c,userFilterDTO))
            {
                retVal.add(c);
            }
        }
        sortCottages(retVal, getStringSortBy(userFilterDTO));
        return retVal;
    }

    private void sortCottages(List<Cottage> cottages, String sortBy) {
        Comparator<Cottage> comparator = selectComparator(sortBy);
        if (sortBy.equalsIgnoreCase("rate")) cottages.sort(Collections.reverseOrder(comparator));
        else cottages.sort(comparator);
    }

    private Comparator<Cottage> selectComparator(String sortBy) {
        switch (sortBy) {
            case "price":
                return new CottagePriceComparator();
            case "rate":
                return new CottageRatingComparator();
            case "city":
                return new CottageCityComparator();
            case "country":
                return new CottageCountryComparator();
            default:
                return new CottageNameComparator();
        }
    }

    private String getStringSortBy(UserFilterDTO userFilterDTO) {
        List<String> sortBy = userFilterDTO.getSortByList();
        int idx = userFilterDTO.getSortBy();
        return sortBy.get(idx).toLowerCase();
    }

    private boolean isValidNumOfPeople(Cottage c, UserFilterDTO userFilterDTO) {
        int cottageNum = c.getNumOfBeds();
        int requested = userFilterDTO.getNumberOfPeople();
        return cottageNum >= requested;
    }

    private boolean isValidCountryAndCity(Cottage c, UserFilterDTO userFilterDTO) {
        if (!userFilterDTO.getCountry().equals("") && !userFilterDTO.getCountry().equalsIgnoreCase(c.getAddress().getCountry())) return false;
        // ako je drzava unesena i nije ono sto je napisano tamo
        if (!userFilterDTO.getCity().equals("") && !userFilterDTO.getCity().equalsIgnoreCase(c.getAddress().getCity())) return false;
        return true;
    }

    private boolean isValidRate(Cottage c, int rate) {
        return c.getRateOrNegativeOne()>=rate || c.getRateOrNegativeOne()==-1;
    }

    private boolean isValidDate(Cottage c, UserFilterDTO userFilterDTO) {
        for(Reservation r : c.getReservations())
        {
            if (r.getReservationStatus()!= ReservationStatus.CANCELLED)
            {
                if (isInMidDate(r.getStart(), userFilterDTO) || isInMidDate(r.getEnd(), userFilterDTO) || isAround(r, userFilterDTO)) return false;
            }
            // ako nadjes bar jednu rezervaciju da joj je pcetni ili krajnji datum unutar nase, vrati false
            // ako nadjes bar jednu rezervaciju da joj je pocetni datum prije nase, a krajni poslije, vrati false
            else
            {
                // za one koje nisu aktivne gledamo jel bila ista takva
                if (parametersAreSame(r, userFilterDTO)) return false;
            }
        }
        return true;
    }

    private boolean parametersAreSame(Reservation r, UserFilterDTO userFilterDTO) {
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


    public void notifySubscribers(Cottage cottage) {
        String title = "Subscription update";
        String content = "There is a new fast reservation available for " + cottage.getName();
        for (Client s : cottage.getSubscribers()) {
            emailSender.send(s.getEmail(), title, content);
        }
    }

    /*public List<Cottage> sort(List<Cottage> cottages, UserFilterDTO userFilterDTO) {

    }*/
}
