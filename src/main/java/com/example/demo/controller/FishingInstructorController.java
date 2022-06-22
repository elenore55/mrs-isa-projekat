package com.example.demo.controller;

import com.example.demo.dto.AdventureDTO;
import com.example.demo.dto.FishingInstructorDTO;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/instructors")
public class FishingInstructorController {
    private FishingInstructorService fishingInstructorService;
    private ReservationService reservationService;
    private AdventureService adventureService;
    private ClientService clientService;
    private OfferService offerService;

    @Autowired
    public FishingInstructorController(FishingInstructorService fishingInstructorService, ReservationService reservationService, AdventureService adventureService, ClientService clientService, OfferService offerService)
    {
        this.fishingInstructorService=fishingInstructorService;
        this.reservationService = reservationService;
        this.adventureService = adventureService;
        this.clientService = clientService;
        this.offerService = offerService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<FishingInstructorDTO>> getAllInstructors() {

        List<FishingInstructor> instructors = fishingInstructorService.findAll();


        List<FishingInstructorDTO> fishingInstructorDTOS = new ArrayList<>();
        for (FishingInstructor fishingInstructor : instructors) {
            fishingInstructorDTOS.add(new FishingInstructorDTO(fishingInstructor));
        }

        return new ResponseEntity<>(fishingInstructorDTOS, HttpStatus.OK);
    }



    @GetMapping(value = "/getInstructorData")
    public ResponseEntity<FishingInstructorDTO> getFishingInstructorData(){
        // pribavi instruktora
        FishingInstructor fishingInstructor = fishingInstructorService.findOne(3);
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
        fishingInstructor.getProfileData().setEmail(fishingInstructorDTO.getProfileDataDTO().getEmail());
        fishingInstructor.getProfileData().setAddress(fishingInstructorDTO.getProfileDataDTO().getAddress());
        fishingInstructor.getProfileData().setName(fishingInstructorDTO.getProfileDataDTO().getName());
        fishingInstructor.getProfileData().setId(fishingInstructorDTO.getProfileDataDTO().getId());
        fishingInstructor.getProfileData().setPassword(fishingInstructorDTO.getProfileDataDTO().getPassword());
        fishingInstructor.getProfileData().setPhoneNumber(fishingInstructorDTO.getProfileDataDTO().getPhoneNumber());
        fishingInstructor.getProfileData().setSurname(fishingInstructorDTO.getProfileDataDTO().getSurname());

        fishingInstructor = fishingInstructorService.save(fishingInstructor);
        return new ResponseEntity<>(new FishingInstructorDTO(fishingInstructor), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteTheAdventure/{idadventure}/{idinstruktor}")
    public ResponseEntity<Void> deleteTheCottage(@PathVariable Integer idadventure,@PathVariable Integer idinstruktor) {
        FishingInstructor fishingInstructor = fishingInstructorService.findOne(idinstruktor);
        if (fishingInstructor == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Reservation> allReservationsOfThisAdventure = this.reservationService.findAll().stream().filter(r-> r.getOffer().getId() == idadventure).collect(Collectors.toList());
        for(Reservation r : allReservationsOfThisAdventure) {
            Offer offer = new Offer();
            offer.setId(-1);
            r.setOffer(offer);
            this.reservationService.save(r);
        }

        List<Client> clients = this.clientService.findAll();
        for(Client c : clients) {
            if (c.getSubscriptionsByID(idadventure)) {
                c.setSubscriptions(c.getSubscriptions().stream().filter(s -> s.getId() != idadventure).collect(Collectors.toList()));
                clientService.save(c);
            }
        }

        fishingInstructor.setAdventures(fishingInstructor.getAdventures().stream().filter(a -> a.getId() != idadventure).collect(Collectors.toList()));
        fishingInstructorService.save(fishingInstructor);
        adventureService.remove(idadventure);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
