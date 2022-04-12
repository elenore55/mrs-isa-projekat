package com.example.demo.controller;

import com.example.demo.dto.AdventureDTO;
import com.example.demo.dto.FishingEquipmentDTO;
import com.example.demo.model.*;
import com.example.demo.service.AdventureService;
import com.example.demo.service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="api/adventures")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;
    @Autowired
    private FishingInstructorService fishingInstructorService;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdventureDTO> getAdventure(@PathVariable Integer id) {

        Adventure adventure = adventureService.findOne(id);

        // adventure must exist
        if (adventure == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<AdventureDTO> saveAdventure(@RequestBody AdventureDTO adventureDTO) {

        Adventure adventure = new Adventure();

        adventure.setId(adventureDTO.getId());
        adventure.setName(adventureDTO.getName());
        adventure.setAddress(adventureDTO.getAddress());
        adventure.setDescription(adventureDTO.getDescription());

        PriceList priceList = new PriceList();
        priceList.setAmount(adventureDTO.getPrice());
        adventure.setPriceList(priceList);

        adventure.setAdditionalInfo(adventureDTO.getAdditionalInfo());

        //treba naci vec postojeceg instruktora => instructor service je potreban
//        FishingInstructor fishingInstructor = new FishingInstructor();
//        fishingInstructor.setId(adventureDTO.getfInstructorId());
        FishingInstructor fishingInstructor = fishingInstructorService.findOne(adventureDTO.getfInstructorId());
        adventure.setInstructor(fishingInstructor);

        List<Image> images = new ArrayList<>();
        for(String imagePath : adventureDTO.getImagePaths())
            images.add(new Image(imagePath));


        List<Rule> rules= new ArrayList<>();
        for(String ruleTxt : adventureDTO.getRules())
            rules.add(new Rule(ruleTxt));

        List<FishingEquipment> fishingEquipmentList = new ArrayList<>();
        for(FishingEquipmentDTO fishingEquipmentListDTO : adventureDTO.getFishingEquipmentList())
            fishingEquipmentList.add(new FishingEquipment(fishingEquipmentListDTO.getName(), fishingEquipmentListDTO.getAmount()));

        adventure = adventureService.save(adventure);
        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.CREATED);
    }
}
