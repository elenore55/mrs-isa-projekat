package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.service.AdventureService;
import com.example.demo.service.FishingEquipmentService;
import com.example.demo.service.FishingInstructorService;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/adventures")
public class AdventureController {

    private AdventureService adventureService;
    private FishingInstructorService fishingInstructorService;
    private FishingEquipmentService fishingEquipmentService;
    private ReservationService reservationService;

    @Autowired
    public AdventureController(AdventureService adventureService, FishingInstructorService fishingInstructorService, FishingEquipmentService fishingEquipmentService, ReservationService reservationService) {
        this.adventureService = adventureService;
        this.fishingInstructorService = fishingInstructorService;
        this.fishingEquipmentService = fishingEquipmentService;
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<AdventureDTO>> getAllAdventures() {

        List<Adventure> adventures = adventureService.findAll();

        // convert adventures to DTOs
        List<AdventureDTO> adventuresDTO = new ArrayList<>();
        for (Adventure adventure : adventures) {
            adventuresDTO.add(new AdventureDTO(adventure));
        }

        return new ResponseEntity<>(adventuresDTO, HttpStatus.OK);
    }
    @GetMapping(path = "/deleteAdventure/{id}")
    public ResponseEntity<Void> deleteAdventure(@PathVariable Integer id) {
        Adventure adventure = adventureService.findOne(id);
        if (adventure == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // dodati proveru ako je napravljena rezervacija!!!
        adventureService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdventureDTO> getAdventure(@PathVariable Integer id) {

        Adventure adventure = adventureService.findOne(id);

        // adventure must exist
        if (adventure == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/addAdventure", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<AdventureDTO> saveAdventure(@RequestBody AdventureDTO adventureDTO) {

        Adventure adventure = new Adventure();


        adventure.setName(adventureDTO.getName());
        adventure.setAddress(new Address(adventureDTO.getAddress().getStreet(),
                adventureDTO.getAddress().getCity(), adventureDTO.getAddress().getCountry()));
        adventure.setDescription(adventureDTO.getDescription());
        adventure.setPriceList(adventureDTO.getPrice());

        adventure.setAdditionalInfo(adventureDTO.getAdditionalInfo());

        //treba naci vec postojeceg instruktora => instructor service je potreban
//        FishingInstructor fishingInstructor = new FishingInstructor();
//        fishingInstructor.setId(adventureDTO.getfInstructorId());
//        FishingInstructor fishingInstructor = fishingInstructorService.findOne(adventureDTO.getfInstructorId());
        FishingInstructor fishingInstructor = fishingInstructorService.findOne(1);
        adventure.setInstructor(fishingInstructor);

        List<Rule> rules = new ArrayList<>();
        for (String ruleTxt : adventureDTO.getRules())
            rules.add(new Rule(ruleTxt));
        adventure.setRules(rules);

        List<FishingEquipment> fishingEquipmentList = new ArrayList<>();
        for (FishingEquipmentDTO fishingEquipmentListDTO : adventureDTO.getFishingEquipmentList()){
            fishingEquipmentList.add( fishingEquipmentService.findOne(fishingEquipmentListDTO.getId()));
        }



        adventure.setFishingEquipments(fishingEquipmentList);


        adventure.setMaxPeople(adventureDTO.getMaxPeople());

        adventure = adventureService.save(adventure);
        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(path = "/updateAdventureInfo",method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<AdventureDTO> updateInstructorInfo(@RequestBody AdventureDTO adventureDTO){
        Adventure adventure = adventureService.findOne(adventureDTO.getId());
        adventure.setId(adventureDTO.getId());
        adventure.setAddress(adventureDTO.getAddress());
        adventure.setName(adventureDTO.getName());
        adventure.setDescription(adventureDTO.getDescription());
        adventure.setPriceList(adventureDTO.getPrice());
        adventure.setAdditionalInfo(adventureDTO.getAdditionalInfo());
        adventure.setMaxPeople(adventureDTO.getMaxPeople());

        List<FishingEquipment> fishingEquipmentList = new ArrayList<>();
//        List<FishingEquipment> fishingEquipmentListALL = fishingEquipmentService.findAll();
        for (FishingEquipmentDTO fishingEquipmentListDTO : adventureDTO.getFishingEquipmentList()) {

            fishingEquipmentList.add(fishingEquipmentService.findOne(fishingEquipmentListDTO.getId()));
        }
        adventure.setFishingEquipments(fishingEquipmentList);

        adventure = adventureService.update(adventure);
        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @RequestMapping(path = "/addFastReservation", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<FastReservationDTO> addFastReservation(@RequestBody FastAdventureReservDTO dto) {
        Adventure adventure = adventureService.findOne(dto.getAdventure_id());
        if (adventure == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        List<FastAdventureReservation> res = adventure.getFastAdventureReservations();
        FastAdventureReservation far = new FastAdventureReservation();
        far.setStart(dto.getStart());
        far.setDuration(dto.getDuration());
//        far.setActionStart();
//        far.setActionDuration();


        far.setPrice(dto.getPrice());
        far.setMaxPeople(dto.getMaxPeople());
        far.setPlace(adventure.getAddress());
        res.add(far);
        adventure.setFastAdventureReservations(res);
        adventureService.save(adventure);
        return new ResponseEntity<>(new FastReservationDTO(far), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/addReservationClient", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReservationDTO> addResevClient(@RequestBody ReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setClient(new Client()); // dopuni
        reservation.setEnd(dto.getEndDate());
        reservation.setStart(dto.getStartDate());
        reservation.setOffer(new Offer()); // dopuni

        if (reservation.getClient() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        reservation.setReservationStatus(ReservationStatus.PENDING);
        reservation = reservationService.save(reservation);

        return new ResponseEntity<>(new ReservationDTO(reservation), HttpStatus.OK);
    }


}
