package com.example.demo.controller;

import com.example.demo.dto.RegistrationRequestDTO;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/registrationRequests")
public class RegistrationRequestController {
    private RegistrationRequestService service;

    @Autowired
    public RegistrationRequestController(RegistrationRequestService service) {
        this.service = service;
    }

    @ResponseBody
    @RequestMapping(path = "/addRequest", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    ResponseEntity<RegistrationRequest> addRequest(@RequestBody RegistrationRequestDTO dto) {
        System.out.println("Request received");
        return null;
    }
}
