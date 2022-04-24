package com.example.demo.controller;

import com.example.demo.dto.ShipDTO;
import com.example.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/ships")
public class ShipController {
    private ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @ResponseBody
    @RequestMapping(path = "/addShip", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ShipDTO> saveShip(@RequestBody ShipDTO shipDTO) {
        System.out.println("SAVING");
        return new ResponseEntity<>(shipDTO, HttpStatus.CREATED);
    }
}
