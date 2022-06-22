package com.example.demo.service;

import com.example.demo.dto.SubDTO;
import com.example.demo.model.*;
import com.example.demo.repository.OfferRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {
    private OfferRepository repository;
    private EmailSender emailSender;

    @Autowired
    public OfferService(OfferRepository repository, EmailSender emailSender) {
        this.repository = repository;
        this.emailSender = emailSender;
    }

    @Transactional
    public Offer findOne(Integer id) {
        return repository.findById(id).orElseGet(null);
    }

    public List<Offer> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Offer save(Offer offer) {
        return repository.save(offer);
    }

    public void remove(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public String getNameForReservationView(Integer id) {
        // ovdje treba na osnovu ida da nadjem kako se zvala ponuda
        // mozda cu dodati da vrati i tip ponude, tipa vikendica Maria
        Offer o = this.findOne(id);
        return o.getName();

    }

    public void setImage(Offer o, SubDTO s) {
        List<Image> images = new ArrayList<>();
        if (o instanceof Cottage) {
            images = ((Cottage) o).getImages();
        }
        if (o instanceof Ship) {
            images = ((Ship) o).getImages();
        }
        if (images.isEmpty()) return;
        s.setImage(images.get(0).getPath());
    }


    public void notifySubscribers(Offer offer) {
        String title = "Subscription update";
        String content = "<h1>There is a new fast reservation available for </h1>" + offer.getName() + "\nCheck it out!";
        for (Client s : offer.getSubscribers()) {
            emailSender.send(s.getEmail(), title, content);
        }
    }
}