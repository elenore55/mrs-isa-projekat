package com.example.demo.controller;

import com.example.demo.dto.ComplaintAdminDTO;
import com.example.demo.dto.ComplaintDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.Client;
import com.example.demo.model.Complaint;
import com.example.demo.model.User;
import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/complaint")
public class ComplaintController {
    private ComplaintService complaintService;
    private UserService userService;

    @Autowired
    public ComplaintController(ComplaintService complaintService, UserService userService)
    {
        this.complaintService = complaintService;
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> addComplaint(@RequestBody ComplaintDTO complaintDTO){
        Complaint c = new Complaint(complaintDTO);
        // ovdje treba pronaci usera koi ju je napisao
//        User u = userService.findById(complaintDTO.getId());
        Client u = (Client) userService.findById(complaintDTO.getId());
        c.setIssuedBy(u);
        complaintService.save(c);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @GetMapping(path = "/allPending")
    public ResponseEntity<List<ComplaintAdminDTO>> getAllPendingComplaints(){
        List<Complaint> complaints = complaintService.findAll();
        List<ComplaintAdminDTO> complaintsDTOS = new ArrayList<>();
        for(Complaint complaint : complaints) {
            if(complaint.getStatus() == AdminApprovalStatus.PENDING)
                complaintsDTOS.add(new ComplaintAdminDTO(complaint));
        }
        return new ResponseEntity<>(complaintsDTOS, HttpStatus.OK);
    }

    @GetMapping(path = "/approvalStatus")
    public ResponseEntity<List<AdminApprovalStatus>> getComplaintPossibleStatuses(){
        List<AdminApprovalStatus> statuses = new ArrayList<>();
        statuses.add(AdminApprovalStatus.APPROVED);
        statuses.add(AdminApprovalStatus.REJECTED);
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Complaint> getComplaint(@PathVariable Integer id)
    {
        Complaint complaint = complaintService.findOne(id);
        if(complaint == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(complaint,HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/updateComplaint", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Complaint> updateComplaint(@RequestBody Complaint complaint)
    {
        Complaint izBaze = complaintService.findOne(complaint.getId());
        izBaze.setStatus(AdminApprovalStatus.APPROVED);
        izBaze = complaintService.update(izBaze);
        return  new ResponseEntity<>(izBaze,HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @RequestMapping(path = "/updateComplaintAdmin", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Complaint> updateComplaintAdmin(@RequestBody ComplaintAdminDTO complaint)
    {
        Complaint izBaze = complaintService.findOne(complaint.getId());
        if(complaint.getAdminApprovalStatus()==AdminApprovalStatus.APPROVED || complaint.getAdminApprovalStatus()==AdminApprovalStatus.REJECTED)
            izBaze.setStatus(complaint.getAdminApprovalStatus());
        izBaze = complaintService.update(izBaze);
        return  new ResponseEntity<>(izBaze,HttpStatus.ACCEPTED);
    }


}
