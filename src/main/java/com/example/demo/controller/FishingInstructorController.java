package com.example.demo.controller;

import com.example.demo.dto.FishingInstructorDTO;
import com.example.demo.model.Address;
import com.example.demo.model.FishingInstructor;
import com.example.demo.model.ProfileData;
import com.example.demo.service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/instructors")
public class FishingInstructorController {
    private FishingInstructorService fishingInstructorService;

    @Autowired
    public FishingInstructorController(FishingInstructorService fishingInstructorService)
    {
        this.fishingInstructorService=fishingInstructorService;
    }
    @GetMapping(value = "/getInstructorData")
    public ResponseEntity<FishingInstructorDTO> getFishingInstructorData(){
        FishingInstructor fishingInstructor = fishingInstructorService.findOne(5);
        FishingInstructorDTO fishingInstructorDTO = new FishingInstructorDTO(fishingInstructor);

        return new ResponseEntity<>(fishingInstructorDTO, HttpStatus.OK);
    }
    @ResponseBody
    @RequestMapping(path = "/updateInstructorInfo",method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<FishingInstructorDTO> updateInstructorInfo(@RequestBody FishingInstructorDTO fishingInstructorDTO){
        FishingInstructor fishingInstructor = fishingInstructorService.findOne(fishingInstructorDTO.getId());

        System.out.println(fishingInstructorDTO.toString());
        fishingInstructor.setId(fishingInstructorDTO.getId());
        fishingInstructor.setBiography(fishingInstructorDTO.getBiography());
//        fishingInstructor.setSurname(fishingInstructorDTO.getSurname());
//        fishingInstructor.setEmail(fishingInstructorDTO.getEmail());
//        fishingInstructor.setPhoneNumber(fishingInstructorDTO.getPhoneNumber());
//        fishingInstructor.setAddress(new Address(fishingInstructorDTO.getAddress().getStreet(),
//                fishingInstructorDTO.getAddress().getCity(),fishingInstructorDTO.getAddress().getCountry()));
//        fishingInstructor.setPassword(fishingInstructorDTO.getPassword());
        fishingInstructor.getProfileData().setEmail(fishingInstructorDTO.getProfileDataDTO().getEmail());
        fishingInstructor.getProfileData().setAddress(fishingInstructorDTO.getProfileDataDTO().getAddress());
        fishingInstructor.getProfileData().setName(fishingInstructorDTO.getProfileDataDTO().getName());
        fishingInstructor.getProfileData().setId(fishingInstructorDTO.getProfileDataDTO().getId());
        fishingInstructor.getProfileData().setPassword(fishingInstructorDTO.getProfileDataDTO().getPassword());
        fishingInstructor.getProfileData().setPhoneNumber(fishingInstructorDTO.getProfileDataDTO().getPhoneNumber());
        fishingInstructor.getProfileData().setSurname(fishingInstructorDTO.getProfileDataDTO().getSurname());
//        fishingInstructor.setProfileData(new ProfileData(fishingInstructorDTO.getProfileDataDTO().getEmail(),
//                fishingInstructorDTO.getProfileDataDTO().getPassword(),fishingInstructorDTO.getProfileDataDTO().getName(),
//                fishingInstructorDTO.getProfileDataDTO().getSurname(),fishingInstructorDTO.getProfileDataDTO().getPhoneNumber(),
//                fishingInstructorDTO.getProfileDataDTO().getAddress()));

        fishingInstructor = fishingInstructorService.save(fishingInstructor);
        return new ResponseEntity<>(new FishingInstructorDTO(fishingInstructor), HttpStatus.CREATED);
    }
}
