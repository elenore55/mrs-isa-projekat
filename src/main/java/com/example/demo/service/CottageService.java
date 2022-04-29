package com.example.demo.service;

import com.example.demo.model.Cottage;
import com.example.demo.model.Reservation;
import com.example.demo.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

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
}
