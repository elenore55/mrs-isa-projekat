package com.example.demo.controller;

import com.example.demo.dto.CottageDTO;
import com.example.demo.dto.RoomDTO;
import com.example.demo.model.*;
import com.example.demo.service.CottageOwnerService;
import com.example.demo.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/cottages")
public class CottageController {
    private CottageService cottageService;
    private CottageOwnerService cottageOwnerService;

    @Autowired
    public CottageController(CottageService cottageService, CottageOwnerService cottageOwnerService) {
        this.cottageService = cottageService;
        this.cottageOwnerService = cottageOwnerService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CottageDTO> saveCottage(@RequestBody CottageDTO cottageDTO) {
        Cottage cottage = new Cottage();
        cottage.setName(cottageDTO.getName());
        cottage.setDescription(cottageDTO.getDescription());
        cottage.setAddress(new Address(cottageDTO.getAddress().getStreet(), cottageDTO.getAddress().getCity(), cottageDTO.getAddress().getCountry()));
        cottage.setPriceList(new PriceList(LocalDate.now(), null, cottageDTO.getPrice()));
        List<Rule> rules = new ArrayList<>();
        for (String ruleText : cottageDTO.getRules()) rules.add(new Rule(ruleText));
        cottage.setRules(rules);
        cottage.setAdditionalInfo(cottageDTO.getAdditionalInfo());
        List<Room> rooms = new ArrayList<>();
        for (RoomDTO dto : cottageDTO.getRooms()) {
            Room room = new Room();
            room.setNumberOfBeds(dto.getNumberOfBeds());
            List<Image> images = new ArrayList<>();
            for (String imgPath : dto.getImagePaths()) images.add(new Image(imgPath));
            room.setImages(images);
            rooms.add(room);
        }
        cottage.setRooms(rooms);
        cottage.setOwner(cottageOwnerService.findOne(cottageDTO.getOwnerId()));
        cottage = cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.CREATED);
    }
}
