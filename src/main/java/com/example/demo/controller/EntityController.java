package com.example.demo.controller;

import com.example.demo.dto.CottageDTO;
import com.example.demo.model.Cottage;
import com.example.demo.service.AdventureService;
import com.example.demo.service.CottageService;
import com.example.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/entities")
public class EntityController {
    private CottageService cottageService;
    private ShipService shipService;
    private AdventureService adventureService;

    @Autowired
    public EntityController(CottageService cottageService, ShipService shipService, AdventureService adventureService)
    {
        this.cottageService = cottageService;
        this.shipService = shipService;
        this.adventureService = adventureService;
    }


}
