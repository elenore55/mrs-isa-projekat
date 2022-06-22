package com.example.demo.controller;

import com.example.demo.dto.DeletionRequestDTO;
import com.example.demo.model.DeletionRequest;
import com.example.demo.model.User;
import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.service.DeletionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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


    @Transactional
    @ResponseBody
    @RequestMapping(path = "/deleteProfile", method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasAnyRole('COTTAGE', 'SHIP', 'CLIENT', 'ADMIN', 'ADVENTURE')")
    public ResponseEntity<String> saveRequest(@RequestBody DeletionRequestDTO deletionRequestDTO)
    {
        if (!deletionRequestService.isDeletePossible(deletionRequestDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User u = deletionRequestService.findById(deletionRequestDTO.getId());
        LocalDateTime now = LocalDateTime.now();
        DeletionRequest d = new DeletionRequest(u, now, AdminApprovalStatus.PENDING);
        deletionRequestService.save(d);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
}


