package com.example.demo.controller;

import com.example.demo.model.Complaint;
import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/complaints")
public class ComplaintController {
    private ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService)
    {
        this.complaintService = complaintService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<List<Complaint>> getAllComplaints(){
        List<Complaint> complaints = complaintService.findAll();
        return new ResponseEntity<>(complaints, HttpStatus.OK);
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
}
