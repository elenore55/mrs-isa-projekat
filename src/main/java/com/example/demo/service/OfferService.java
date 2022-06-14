package com.example.demo.service;

import com.example.demo.dto.SubDTO;
import com.example.demo.model.Cottage;
import com.example.demo.model.Image;
import com.example.demo.model.Client;
import com.example.demo.model.Cottage;
import com.example.demo.model.Offer;
import com.example.demo.model.Ship;
import com.example.demo.repository.OfferRepository;
import org.apache.catalina.LifecycleState;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.tools.JavaCompiler;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private OfferRepository repository;
    private EmailSender emailSender;

    @Autowired
    public OfferService(OfferRepository repository, EmailSender emailSender) {
        this.repository = repository;
        this.emailSender = emailSender;
    }

    public Offer findOne(Integer id) {
        return repository.findById(id).orElseGet(null);
    }

    public Offer save(Offer offer) {
        return repository.save(offer);
    }

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
        if (images.size()==0) return;
        s.setImage(images.get(0).getPath());
    }
}

    public void notifySubscribers(Offer offer) {
        String title = "Subscription update";
        String content = "There is a new fast reservation available for " + offer.getName();
        for (Client s : offer.getSubscribers()) {
            emailSender.send(s.getEmail(), title, content);
        }
    }
}