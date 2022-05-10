package com.example.demo.controller;

import com.example.demo.dto.AdminDTO;
import com.example.demo.dto.AdventureDTO;
import com.example.demo.model.Admin;
import com.example.demo.model.ProfileData;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService)
    {
        this.adminService = adminService;
    }
    @GetMapping(value = "/getAdminData")
    public ResponseEntity<AdminDTO> getAdminData(){
        Admin admin = adminService.findOne(1);
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

}
