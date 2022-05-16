package com.example.demo.controller;

import com.example.demo.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationRequestController {
    private RegistrationRequestService service;

    @Autowired
    public RegistrationRequestController(RegistrationRequestService service) {
        this.service = service;
    }


}
