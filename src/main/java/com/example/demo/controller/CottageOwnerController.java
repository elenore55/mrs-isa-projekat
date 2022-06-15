package com.example.demo.controller;

import com.example.demo.dto.CottageDTO;
import com.example.demo.dto.FilterCottageDTO;
import com.example.demo.model.*;
import com.example.demo.service.CottageOwnerService;
import com.example.demo.service.CottageService;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/cottageOwner")
public class CottageOwnerController {
    private CottageOwnerService service;
    private ReservationService reservationService;
    private CottageService cottageService;

    @Autowired
    public CottageOwnerController(CottageOwnerService service, ReservationService reservationService, CottageService cottageService) {
        this.service = service;
        this.reservationService = reservationService;
        this.cottageService = cottageService;
    }

    @ResponseBody
    @RequestMapping(path = "/getCottages/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CottageDTO>> getCottages(@PathVariable Integer id) {
        CottageOwner owner = service.findOne(id);
        List<Cottage> cottages = owner.getCottages();
        List<CottageDTO> dtos = new ArrayList<>();
        for (Cottage c : cottages) {
            c.getRules();
            c.getImages();
            dtos.add(new CottageDTO(c));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getCottages/{id}/{search}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CottageDTO>> getCottages(@PathVariable Integer id, @PathVariable String search) {
        List<Cottage> cottages = service.searchCottages(id, search.toLowerCase());
        List<CottageDTO> dtos = new ArrayList<>();
        for (Cottage c : cottages) {
            dtos.add(new CottageDTO(c));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/filterCottages/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<CottageDTO>> filterCottages(@PathVariable Integer id, @RequestBody FilterCottageDTO filter) {
        List<Cottage> cottages = service.filterCottages(id, filter);
        List<CottageDTO> dtos = new ArrayList<>();
        for (Cottage c : cottages) {
            dtos.add(new CottageDTO(c));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteTheCottage/{idcottage}/{idvlasnik}")
    public ResponseEntity<Void> deleteTheCottage(@PathVariable Integer idcottage,@PathVariable Integer idvlasnik) {
        CottageOwner cottageOwner = service.findOne(idvlasnik);
        if (cottageOwner == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Reservation> allReservationsOfThisCottage = this.reservationService.findAll().stream().filter(r-> r.getOffer().getId() == idcottage).collect(Collectors.toList());
        for(Reservation r : allReservationsOfThisCottage) {
            r.setOffer(null);
            this.reservationService.save(r);
        }

        Cottage cottage = cottageService.findOne(idcottage);
        cottageOwner.getCottages().remove(cottage);

        service.save(cottageOwner);
        cottageService.remove(cottage.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
