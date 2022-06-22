package com.example.demo.controller;

import com.example.demo.dto.ComplaintAdminDTO;
import com.example.demo.dto.DeleteReqDTO;
import com.example.demo.dto.DeletionRequestDTO;
import com.example.demo.model.Complaint;
import com.example.demo.model.DeletionRequest;
import com.example.demo.model.User;
import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.service.DeletionRequestService;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/deletionRequests")
public class DeletionRequestController {
    private DeletionRequestService deletionRequestService;
    private EmailSender emailSender;

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

    @ResponseBody
    @RequestMapping(path = "/updateDeletionReqAdmin", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<DeletionRequest> updateComplaintAdmin(@RequestBody DeleteReqDTO deletionRequestDTO)
    {
        System.out.println(deletionRequestDTO.toString());
        DeletionRequest izBaze = deletionRequestService.findOne(Integer.valueOf(deletionRequestDTO.getId()));
        if(deletionRequestDTO.getStatus()==AdminApprovalStatus.APPROVED || deletionRequestDTO.getStatus()==AdminApprovalStatus.REJECTED)
            izBaze.setStatus(deletionRequestDTO.getStatus());
        izBaze = deletionRequestService.update(izBaze);

        emailSender.send(izBaze.getSentBy().getEmail(), "Deletion req", izBaze.getStatus().toString());

        return  new ResponseEntity<>(izBaze,HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/allPending")
    public ResponseEntity<List<DeleteReqDTO>> getAllPendingDeletionReqs(){
        List<DeletionRequest> deletionRequests = deletionRequestService.findAll();
        List<DeleteReqDTO> deletionRequestDTOS = new ArrayList<>();
        for(DeletionRequest request : deletionRequests) {
            if(request.getStatus() == AdminApprovalStatus.PENDING)
                deletionRequestDTOS.add(new DeleteReqDTO(request));
        }
        return new ResponseEntity<>(deletionRequestDTOS, HttpStatus.OK);
    }

    @GetMapping(path = "/approvalStatus")
    public ResponseEntity<List<AdminApprovalStatus>> getComplaintPossibleStatuses(){
        List<AdminApprovalStatus> statuses = new ArrayList<>();
        statuses.add(AdminApprovalStatus.APPROVED);
        statuses.add(AdminApprovalStatus.REJECTED);
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }
}


