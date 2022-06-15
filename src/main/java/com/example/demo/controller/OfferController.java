package com.example.demo.controller;

import com.example.demo.dto.FastReservationDTO;
import com.example.demo.model.*;
import com.example.demo.dto.OfferDTO;
import com.example.demo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @ResponseBody
    @RequestMapping(path = "/getFastReservations/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<FastReservationDTO>> getFastReservations(@PathVariable Integer id) {
        Offer o = offerService.findOne(id);
        System.out.println("Here");
        if (o == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<FastReservationDTO> result = new ArrayList<>();
        if (o instanceof Cottage) {
            Cottage c = (Cottage) o;
            for (FastReservation fr : c.getFastReservations()) {
                result.add(new FastReservationDTO(fr));
            }
        } else {
            Ship s = (Ship) o;
            for (FastReservation fr : s.getFastReservations()) {
                result.add(new FastReservationDTO(fr));
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/addFastReservation/{id}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<FastReservationDTO> addFastReservation(@PathVariable Integer id, @RequestBody FastReservationDTO dto) {
        Offer o = offerService.findOne(id);
        if (o == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        FastReservation fr = new FastReservation();
        fr.setStart(dto.getStart());
        fr.setDuration(dto.getDuration());
        fr.setActionStart(dto.getActionStart());
        fr.setActionDuration(dto.getActionDuration());
        fr.setPrice(dto.getPrice());
        fr.setMaxPeople(dto.getMaxPeople());
        if (o instanceof Cottage) {
            Cottage c = (Cottage)o;
            List<FastReservation> res = c.getFastReservations();
            res.add(fr);
            c.setFastReservations(res);
            offerService.save(c);
            offerService.notifySubscribers(c);
        } else if(o instanceof Ship) {
            Ship s = (Ship)o;
            List<FastReservation> res = s.getFastReservations();
            res.add(fr);
            s.setFastReservations(res);
            offerService.save(s);
            offerService.notifySubscribers(s);
        } else
        {
            Adventure a = (Adventure)o;
            List<FastAdventureReservation> res = a.getFastAdventureReservations();
            res.add((FastAdventureReservation) fr);
            a.setFastAdventureReservations(res);
            offerService.save(a);
            offerService.notifySubscribers(a);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getOffer/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OfferDTO> getOffer(@PathVariable Integer id) {
        Offer offer = offerService.findOne(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new OfferDTO(offer), HttpStatus.OK);
    }


}
