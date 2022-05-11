package com.example.demo.controller;

import com.example.demo.dto.AvailabilityDTO;
import com.example.demo.model.Availability;
import com.example.demo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/availability")
public class AvailabilityController {

    private AvailabilityService availabilityService;

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService)
    {
        this.availabilityService = availabilityService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<AvailabilityDTO>> getAllAvailabilities() {

        List<Availability> availabilities = availabilityService.findAll();
        List<AvailabilityDTO> availabilityDTOS = new ArrayList<>();

        for (Availability availability : availabilities) {
            availabilityDTOS.add(new AvailabilityDTO(availability));
        }

        return new ResponseEntity<>(availabilityDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<AvailabilityDTO>> getInstructorsAvailabilities(@PathVariable Integer id) {

        List<Availability> availabilities = availabilityService.findAll();
        List<AvailabilityDTO> availabilityDTOS = new ArrayList<>();

        for (Availability availability : availabilities) {
            if(availability.getId() == id)
                availabilityDTOS.add(new AvailabilityDTO(availability));
        }

        return new ResponseEntity<>(availabilityDTOS, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/addAvailability", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<AvailabilityDTO> saveAvailability(@RequestBody AvailabilityDTO availabilityDTO) {
        Availability availability = new Availability();
        availability.setStart(availabilityDTO.getStart());
        availability.setEnd(availability.getEnd());
        availability.setOffer(availabilityDTO.getOffer());
        return new ResponseEntity<>(new AvailabilityDTO(availability), HttpStatus.CREATED);
    }
}
