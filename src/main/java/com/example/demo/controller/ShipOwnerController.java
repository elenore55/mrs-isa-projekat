package com.example.demo.controller;

import com.example.demo.dto.FilterShipDTO;
import com.example.demo.dto.ShipDTO;
import com.example.demo.model.Offer;
import com.example.demo.model.Reservation;
import com.example.demo.model.Ship;
import com.example.demo.model.ShipOwner;
import com.example.demo.service.ReservationService;
import com.example.demo.service.ShipOwnerService;
import com.example.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/shipOwner")
public class ShipOwnerController {
    private ShipOwnerService service;
    private ShipService shipservice;
    private ReservationService reservationService;


    @Autowired
    public ShipOwnerController(ShipOwnerService service, ShipService shipservice, ReservationService reservationService) {
        this.service = service;
        this.shipservice = shipservice;
        this.reservationService = reservationService;
    }

    @ResponseBody
    @RequestMapping(path = "/getShips/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ShipDTO>> getShips(@PathVariable Integer id) {
        ShipOwner owner = service.findOne(id);
        List<Ship> ships = owner.getShips();
        List<ShipDTO> dtos = new ArrayList<>();
        for (Ship s : ships) {
            dtos.add(new ShipDTO(s));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getShips/{id}/{search}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ShipDTO>> getCottages(@PathVariable Integer id, @PathVariable String search) {
        List<Ship> ships = service.searchShips(id, search.toLowerCase());
        List<ShipDTO> dtos = new ArrayList<>();
        for (Ship s : ships) {
            dtos.add(new ShipDTO(s));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/filterShips/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<ShipDTO>> filterShips(@PathVariable Integer id, @RequestBody FilterShipDTO filter) {
        List<Ship> ships = service.filterShips(id, filter);
        List<ShipDTO> dtos = new ArrayList<>();
        for (Ship s : ships) {
            dtos.add(new ShipDTO(s));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteTheShip/{idbrod}/{idvlasnik}")
    public ResponseEntity<Void> deleteTheShip(@PathVariable Integer idbrod,@PathVariable Integer idvlasnik) {
        ShipOwner shipOwner = service.findOne(idvlasnik);
        if (shipOwner == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Reservation> allReservationsOfThisShip = this.reservationService.findAll().stream().filter(r-> r.getOffer().getId() == idbrod).collect(Collectors.toList());
        for(Reservation r : allReservationsOfThisShip) {
            Offer offer = new Offer();
            offer.setId(-1);
            r.setOffer(offer);
            this.reservationService.save(r);
        }

        Ship ship = shipservice.findOne(idbrod);
        shipOwner.getShips().remove(ship);

        service.save(shipOwner);
        shipservice.remove(ship.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
