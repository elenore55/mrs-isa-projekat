package com.example.demo.controller;

import com.example.demo.dto.DeletionRequestDTO;
import com.example.demo.model.DeletionRequest;
import com.example.demo.model.User;
import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.service.DeletionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "api/deletionRequests")
public class DeletionRequestController {
    private DeletionRequestService deletionRequestService;

    @Autowired
    public DeletionRequestController (DeletionRequestService deletionRequestService)
    {
        this.deletionRequestService = deletionRequestService;
    }

    @ResponseBody
    @RequestMapping(path = "/deleteProfile", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> saveRequest(@RequestBody DeletionRequestDTO deletionRequestDTO)
    {
        User u = deletionRequestService.findById(deletionRequestDTO.getId());
        LocalDateTime now = LocalDateTime.now();
        DeletionRequest d = new DeletionRequest(u, now, AdminApprovalStatus.PENDING);
        deletionRequestService.save(d);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
}


