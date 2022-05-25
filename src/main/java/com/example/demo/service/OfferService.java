package com.example.demo.service;

import com.example.demo.model.Offer;
import com.example.demo.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferService {
    private OfferRepository repository;

    @Autowired
    public OfferService(OfferRepository repository) {
        this.repository = repository;
    }

    public Offer findOne(Integer id) {
        return repository.findById(id).orElseGet(null);
    }

    public String getNameForReservationView(Integer id) {
        // ovdje treba na osnovu ida da nadjem kako se zvala ponuda
        // mozda cu dodati da vrati i tip ponude, tipa vikendica Maria
        Offer o = this.findOne(id);
        return o.getName();

    }
}
