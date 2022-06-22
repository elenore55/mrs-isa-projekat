package com.example.demo.controller;

import com.example.demo.dto.RegistrationRequestAdminDTO;
import com.example.demo.dto.RegistrationRequestDTO;
import com.example.demo.model.Address;
import com.example.demo.model.ProfileData;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/registrationRequests")
public class RegistrationRequestController {
    private RegistrationRequestService service;
    private UserService userService;
    private Profile_DataService profile_dataService;
    private CottageOwnerService cottageOwnerService;
    private ShipOwnerService shipOwnerService;
    private FishingInstructorService fishingInstructorService;
    private AddressService addressService;

    @Autowired
    public RegistrationRequestController(RegistrationRequestService service, UserService userService, Profile_DataService profile_dataService, CottageOwnerService cottageOwnerService, ShipOwnerService shipOwnerService, FishingInstructorService fishingInstructorService, AddressService addressService) {
        this.service = service;
        this.userService = userService;
        this.profile_dataService = profile_dataService;
        this.cottageOwnerService = cottageOwnerService;
        this.shipOwnerService = shipOwnerService;
        this.fishingInstructorService = fishingInstructorService;
        this.addressService = addressService;
    }

    @Transactional
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
        service.notifyAdmins();
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @GetMapping(path = "/approvalStatus")
    public ResponseEntity<List<AdminApprovalStatus>> getComplaintPossibleStatuses() {
        List<AdminApprovalStatus> statuses = new ArrayList<>();
        statuses.add(AdminApprovalStatus.APPROVED);
        statuses.add(AdminApprovalStatus.REJECTED);
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/updateRegReqAdmin", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<RegistrationRequest> updateRegReqAdmin(@RequestBody RegistrationRequestAdminDTO requestAdminDTO) {
        RegistrationRequest izBaze = service.findOne(requestAdminDTO.getId());
        if (requestAdminDTO.getStatus() == AdminApprovalStatus.APPROVED || requestAdminDTO.getStatus() == AdminApprovalStatus.REJECTED) {
            izBaze.setApprovalStatus(requestAdminDTO.getStatus());
        }
        izBaze.setProfileData(null);
        izBaze = service.update(izBaze);

        ProfileData profileData = profile_dataService.getByEmail(requestAdminDTO.getEmail());
        int addresID = profileData.getAddress().getId();
        Address address = new Address(profileData.getAddress());
        profileData.setAddress(null);
        profile_dataService.save(profileData);
        addressService.remove(addresID);
        profile_dataService.remove(profileData.getId());

        if (requestAdminDTO.getStatus() == AdminApprovalStatus.APPROVED) {
            profileData.setAddress(address);
            userService.saveOwners(profileData, requestAdminDTO.getType());
        }

        return new ResponseEntity<>(izBaze, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/allPending")
    public ResponseEntity<List<RegistrationRequestAdminDTO>> getAllPendingRegReqs() {
        List<RegistrationRequest> requests = service.findAll();
        List<RegistrationRequestAdminDTO> registrationRequestAdminDTOS = new ArrayList<>();
        for (RegistrationRequest request : requests) {
            if (request.getApprovalStatus() == AdminApprovalStatus.PENDING)
                registrationRequestAdminDTOS.add(new RegistrationRequestAdminDTO(request));
        }
        return new ResponseEntity<>(registrationRequestAdminDTOS, HttpStatus.OK);
    }
}
