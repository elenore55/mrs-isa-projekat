package com.example.demo.controller;

import com.example.demo.dto.ProfileDataDTO;
import com.example.demo.model.ProfileData;
import com.example.demo.service.Profile_DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/profileData")
public class ProfileDataController {

    private Profile_DataService profile_dataService;

    @Autowired
    public ProfileDataController(Profile_DataService  profile_dataService)
    {
        this.profile_dataService = profile_dataService;
    }

    public ResponseEntity<ProfileDataDTO> getProfileData(@PathVariable Integer id){
        ProfileData profileData = profile_dataService.findOne(id);
        return new ResponseEntity<>(new ProfileDataDTO(profileData),HttpStatus.OK);
    }
}
