package com.example.demo.controller;

import com.example.demo.dto.AvailabilityDTO;
import com.example.demo.model.Availability;
import com.example.demo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
            if(availability.getOffer() == null)
                availabilityDTOS.add(new AvailabilityDTO(availability));
        }

        return new ResponseEntity<>(availabilityDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<AvailabilityDTO>> getInstructorsAvailabilities(@PathVariable Integer id) {

        List<Availability> availabilities = availabilityService.findAll();
        List<AvailabilityDTO> availabilityDTOS = new ArrayList<>();

        for (Availability availability : availabilities) {
            if(availability.getId().equals(id))
                availabilityDTOS.add(new AvailabilityDTO(availability));
        }

        return new ResponseEntity<>(availabilityDTOS, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/addAvailability", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<AvailabilityDTO> saveAvailability(@RequestBody AvailabilityDTO availabilityDTO) {
        Availability availability = new Availability();
        availability.setStart(availabilityDTO.getStart());
        availability.setEnd(availabilityDTO.getEnd());
        availability.setOffer(availabilityDTO.getOffer());

        availability = availabilityService.save(availability);
        return new ResponseEntity<>(new AvailabilityDTO(availability), HttpStatus.CREATED);
    }

    @GetMapping(value = "/betweenDates/{start}/{end}")
    public ResponseEntity<List<AvailabilityDTO>> getBetweenAvailabilities(@PathVariable LocalDateTime start,@PathVariable LocalDateTime end) {

        List<Availability> availabilities = availabilityService.findAll();
        List<AvailabilityDTO> availabilityDTOS = new ArrayList<>();

        for (Availability availability : availabilities) {
            if(availability.getStart().isAfter(start) && availability.getEnd().isBefore(end))
                availabilityDTOS.add(new AvailabilityDTO(availability));
        }

        return new ResponseEntity<>(availabilityDTOS, HttpStatus.OK);
    }
}
