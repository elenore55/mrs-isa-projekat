package com.example.demo.service;

import com.example.demo.dto.SubDTO;
import com.example.demo.model.Cottage;
import com.example.demo.model.Image;
import com.example.demo.model.Offer;
import com.example.demo.model.Ship;
import com.example.demo.repository.OfferRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.tools.JavaCompiler;
import java.util.ArrayList;
import java.util.List;
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
