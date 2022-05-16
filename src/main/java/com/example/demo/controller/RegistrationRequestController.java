package com.example.demo.controller;

import com.example.demo.dto.RegistrationRequestDTO;
import com.example.demo.model.Address;
import com.example.demo.model.ProfileData;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.service.RegistrationRequestService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "api/registrationRequests")
public class RegistrationRequestController {
    private RegistrationRequestService service;
    private UserService userService;

    @Autowired
    public RegistrationRequestController(RegistrationRequestService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(path = "/addRequest", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    ResponseEntity<RegistrationRequest> addRequest(@RequestBody RegistrationRequestDTO dto) {
        String email = dto.getEmail();
        if (userService.isAlreadyRegistered(email))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        RegistrationRequest request = new RegistrationRequest();
        request.setApprovalStatus(AdminApprovalStatus.PENDING);
        Address address = new Address(dto.getAddress().getStreet(), dto.getAddress().getCity(), dto.getAddress().getCountry());
        ProfileData pd = new ProfileData(email, dto.getPassword(), dto.getName(), dto.getSurname(), dto.getPhoneNumber(), address);
        request.setProfileData(pd);
        request.setDateTime(LocalDateTime.now());
        request.setReason(dto.getReason());
        request.setRegistrationType(dto.getRegistrationType());
        request = service.save(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
