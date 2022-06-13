package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Cottage;
import com.example.demo.model.Offer;
import com.example.demo.repository.OfferRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void notifySubscribers(Offer offer) {
        String title = "Subscription update";
        String content = "There is a new fast reservation available for " + offer.getName();
        for (Client s : offer.getSubscribers()) {
            emailSender.send(s.getEmail(), title, content);
        }
    }
}