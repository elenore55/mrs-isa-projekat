package com.example.demo.controller;

import com.example.demo.dto.FilterShipDTO;
import com.example.demo.dto.ShipDTO;
import com.example.demo.model.Ship;
import com.example.demo.model.ShipOwner;
import com.example.demo.service.ShipOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/shipOwner")
public class ShipOwnerController {
    private ShipOwnerService service;

    @Autowired
    public ShipOwnerController(ShipOwnerService service) {
        this.service = service;
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
}
