package com.example.demo.controller;

import com.example.demo.dto.CottageDTO;
import com.example.demo.model.Cottage;
import com.example.demo.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/cottages")
public class CottageController {
    private CottageService cottageService;

    @Autowired
    public CottageController(CottageService cottageService) {
        this.cottageService = cottageService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CottageDTO> saveCottage(@RequestBody CottageDTO cottageDTO) {
        Cottage cottage = new Cottage();
        cottage = cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(), HttpStatus.CREATED);
    }
}
