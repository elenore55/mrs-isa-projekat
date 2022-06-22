package com.example.demo.controller;

import com.example.demo.dto.AdminDTO;
import com.example.demo.dto.AdventureDTO;
import com.example.demo.dto.FishingInstructorDTO;
import com.example.demo.model.Admin;
import com.example.demo.model.FishingInstructor;
import com.example.demo.model.ProfileData;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@RestController
@RequestMapping(value = "api/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService)
    {
        this.adminService = adminService;
    }
    @GetMapping(value = "/getAdminData/{id}")
    public ResponseEntity<AdminDTO> getAdminData(@PathVariable Integer id){
        // ZAPUCANA 2
        Admin admin = adminService.findOne(id);
        AdminDTO adminDTO = new AdminDTO(admin);

        return new ResponseEntity<>(adminDTO, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/addAdmin", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<AdminDTO> saveAdmin(@RequestBody AdminDTO adminDTO){
        Admin admin = new Admin();
        admin.setProfileData(new ProfileData(adminDTO.getProfileDataDTO()));
        admin.setMain(adminDTO.getIs_main());
        admin = adminService.save(admin);
        return new ResponseEntity<>(new AdminDTO(admin),HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/updateAdminInfo",method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<AdminDTO> updateInstructorInfo(@RequestBody AdminDTO adminDTO){

        System.out.println(adminDTO.toString());
        Admin admin = adminService.findOne(adminDTO.getId());
        admin.setMain(adminDTO.getIs_main());
        admin.setEmail(adminDTO.getProfileDataDTO().getEmail());
        admin.setAddress(adminDTO.getProfileDataDTO().getAddress());
        admin.setName(adminDTO.getProfileDataDTO().getName());
        admin.setPassword(adminDTO.getProfileDataDTO().getPassword());
        admin.setPhoneNumber(adminDTO.getProfileDataDTO().getPhoneNumber());
        admin.setSurname(adminDTO.getProfileDataDTO().getSurname());

        admin = adminService.save(admin);
        return new ResponseEntity<>(new AdminDTO(admin), HttpStatus.CREATED);
    }

}
