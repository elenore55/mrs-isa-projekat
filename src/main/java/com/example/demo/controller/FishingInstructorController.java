package com.example.demo.controller;

import com.example.demo.dto.FishingInstructorDTO;
import com.example.demo.model.FishingInstructor;
import com.example.demo.service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
