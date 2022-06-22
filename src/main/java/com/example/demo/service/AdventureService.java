package com.example.demo.service;

import com.example.demo.dto.UserFilterDTO;
import com.example.demo.model.*;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.repository.*;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdventureService {
    @Autowired
    private AdventureRepository adventureRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private FishingEquipmentRepository fishingEquipmentRepository;

    private EmailSender emailSender;



    public Adventure findOne(Integer id) {
        return adventureRepository.getById(id);
    }

    public List<Adventure> findAll(){
        return  adventureRepository.findAll();
    }

    public Adventure save(Adventure adventure) {
        return adventureRepository.save(adventure);
    }

    public Adventure update(Adventure adventure){
        return adventureRepository.save(adventure);
    }

    public void remove(Integer id) {
        adventureRepository.deleteById(id);
    }

    public List<Adventure> filter(UserFilterDTO userFilterDTO) {
        List<Adventure> retVal = new ArrayList<>();
        List<Adventure> all = adventureRepository.findAll();
        for (Adventure a : all) {
            if (isValidDate(a, userFilterDTO) && isValidRate(a, userFilterDTO) &&
                    isValidCountryAndCity(a, userFilterDTO) && isValidNumOfPeople(a, userFilterDTO)) {
                retVal.add(a);
            }
        }
        //sortShips(retVal, getStringSortBy(userFilterDTO));
        return retVal;
    }

    private boolean isValidDate(Adventure s, UserFilterDTO userFilterDTO) {
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

    private boolean isValidCountryAndCity(Adventure s, UserFilterDTO userFilterDTO) {
        if (!userFilterDTO.getCountry().equals("") && !userFilterDTO.getCountry().equalsIgnoreCase(s.getAddress().getCountry()))
            return false;
        // ako je drzava unesena i nije ono sto je napisano tamo
        if (!userFilterDTO.getCity().equals("") && !userFilterDTO.getCity().equalsIgnoreCase(s.getAddress().getCity()))
            return false;
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

    private boolean isValidRate(Adventure a, UserFilterDTO u) {
        if (u.getRate()!=0)
        {
            return a.getRateOrNegativeOne() >= u.getRate() || a.getRateOrNegativeOne() == -1;
        }
        return true;
    }

    private boolean isValidNumOfPeople(Adventure s, UserFilterDTO userFilterDTO) {
        int num = s.getMaxPeople();
        int requested = userFilterDTO.getNumberOfPeople();
        return num >= requested;
    }
}
