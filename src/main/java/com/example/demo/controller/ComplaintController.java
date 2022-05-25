package com.example.demo.controller;

import com.example.demo.dto.ComplaintDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.Complaint;
import com.example.demo.model.User;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        User u = userService.findById(complaintDTO.getId());
        c.setIssuedBy(u);
        complaintService.save(c);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }


}
