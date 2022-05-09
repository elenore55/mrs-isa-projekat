package com.example.demo.controller;

import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
    private ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }
}
