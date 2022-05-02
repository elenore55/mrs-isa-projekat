package com.example.demo.controller;

import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/addresses")
public class AddressController {
    private AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    @ResponseBody
    @RequestMapping(path = "/getCities", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<String>> getCities() {
        return new ResponseEntity<>(service.getCities(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getCountries", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<String>> getCountries() {
        return new ResponseEntity<>(service.getCountries(), HttpStatus.OK);
    }
}
