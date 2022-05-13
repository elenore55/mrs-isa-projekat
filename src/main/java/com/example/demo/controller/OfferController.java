package com.example.demo.controller;

import com.example.demo.model.Offer;
import com.example.demo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/offers")
public class OfferController {
    private OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @ResponseBody
    @RequestMapping(path = "/getName/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getName(@PathVariable Integer id) {
        Offer offer = offerService.findOne(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer.getName(), HttpStatus.OK);
    }
}
