package com.example.demo.service;

import com.example.demo.dto.UserFilterDTO;
import com.example.demo.model.Cottage;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.SchemaOutputResolver;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CottageService {

    private final CottageRepository cottageRepository;

    @Autowired
    public CottageService(CottageRepository cottageRepository) {
        this.cottageRepository = cottageRepository;
    }

    public Cottage save(Cottage cottage) {
        return cottageRepository.save(cottage);
    }

    public List<Cottage> getCottages()
    {
        return cottageRepository.findAll();
    }

    @Transactional
    public Cottage findOne(Integer id) {
        return cottageRepository.findById(id).orElseGet(null);
    }

    public void remove(Integer id) {
        cottageRepository.deleteById(id);
    }

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
            System.out.println("Date je " + isValidDate(c, userFilterDTO));
            System.out.println("Ocjena je " + isValidRate(c,userFilterDTO.getRate()));
            System.out.println("Drzava i grad je " +isValidCountryAndCity(c, userFilterDTO));
            if (isValidDate(c, userFilterDTO) && isValidRate(c,userFilterDTO.getRate()) &&
            isValidCountryAndCity(c, userFilterDTO))
            {
                retVal.add(c);
            }
        }
        System.out.println("########################################################################################");
        System.out.println("start date: " + userFilterDTO.getFromDate());
        System.out.println("end date: " + userFilterDTO.getToDate());
        System.out.println("rate: " + userFilterDTO.getFromDate());
        System.out.println("start date: " + userFilterDTO.getFromDate());
        System.out.println("start date: " + userFilterDTO.getFromDate());

        return retVal;

    }

    private boolean isValidCountryAndCity(Cottage c, UserFilterDTO userFilterDTO) {
        System.out.println("Drzava iz filtera je " + userFilterDTO.getCountry());
        if (!userFilterDTO.getCountry().equals("") && !userFilterDTO.getCountry().equalsIgnoreCase(c.getAddress().getCountry())) return false;
        // ako je drzava unesena i nije ono sto je napisano tamo
        if (!userFilterDTO.getCity().equals("") && !userFilterDTO.getCity().equalsIgnoreCase(c.getAddress().getCity())) return false;
        return true;
    }

    private boolean isValidRate(Cottage c, int rate) {
        System.out.println("Prosjecna ocjena je sada " + c.getRateOrNegativeOne());
        return c.getRateOrNegativeOne()>=rate || c.getRateOrNegativeOne()==-1;
    }

    private boolean isValidDate(Cottage c, UserFilterDTO userFilterDTO) {
        for(Reservation r : c.getReservations())
        {
            // ako nadjes bar jednu rezervaciju da joj je pocetni datum poslije pocetka nase, a prije kraja nase, vrati false
            // ako nadjes bar jednu rezervaciju da joj je krajnji datum poslije pocetka nase, a prije kraja nase, vrati false
            if (isInMidDate(r.getStart(), userFilterDTO) || isInMidDate(r.getEnd(), userFilterDTO)) return false;
        }
        return true;
    }

    private boolean isInMidDate(LocalDateTime cottageDate, UserFilterDTO userFilterDTO) {
        // gledamo da li je ovaj date izmedju mojih, koji nisu formatirani
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String startDate = userFilterDTO.getFromDate();
        String endDate = userFilterDTO.getToDate();

        LocalDate localStart = LocalDate.parse(startDate, formatter);
        LocalDate localEnd = LocalDate.parse(endDate, formatter);

        LocalDateTime localDateTimeStart = localStart.atStartOfDay();
        LocalDateTime localDateTimeEnd = localEnd.atStartOfDay();

        return localDateTimeStart.isBefore(cottageDate) && cottageDate.isBefore(localDateTimeEnd);
    }


}
