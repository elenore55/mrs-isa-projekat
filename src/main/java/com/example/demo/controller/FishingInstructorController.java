package com.example.demo.controller;

import com.example.demo.dto.FishingInstructorDTO;
import com.example.demo.model.Address;
import com.example.demo.model.FishingInstructor;
import com.example.demo.service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
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
        FishingInstructor fishingInstructor = fishingInstructorService.findOne(1);
        FishingInstructorDTO fishingInstructorDTO = new FishingInstructorDTO(fishingInstructor);

        return new ResponseEntity<>(fishingInstructorDTO, HttpStatus.OK);
    }
    @ResponseBody
    @RequestMapping(path = "/updateInstructorInfo",method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<FishingInstructorDTO> updateInstructorInfo(@RequestBody FishingInstructorDTO fishingInstructorDTO){
        FishingInstructor fishingInstructor = fishingInstructorService.findOne(fishingInstructorDTO.getId());

        fishingInstructor.setId(fishingInstructorDTO.getId());
        fishingInstructor.setSurname(fishingInstructorDTO.getSurname());
        fishingInstructor.setEmail(fishingInstructorDTO.getEmail());
        fishingInstructor.setPhoneNumber(fishingInstructorDTO.getPhoneNumber());
        fishingInstructor.setAddress(new Address(fishingInstructorDTO.getAddress().getStreet(),
                fishingInstructorDTO.getAddress().getCity(),fishingInstructorDTO.getAddress().getCountry()));
        fishingInstructor.setBiography(fishingInstructorDTO.getBiography());
        fishingInstructor.setPassword(fishingInstructorDTO.getPassword());
        fishingInstructor.setBiography(fishingInstructorDTO.getBiography());

        fishingInstructor = fishingInstructorService.save(fishingInstructor);
        return new ResponseEntity<>(new FishingInstructorDTO(fishingInstructor), HttpStatus.ACCEPTED);
    }
}
