package com.example.demo.service;

import com.example.demo.model.Cottage;
import com.example.demo.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Cottage findOne(Integer id) {
        return cottageRepository.findById(id).orElseGet(null);
    }

}
