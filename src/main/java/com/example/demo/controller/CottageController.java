package com.example.demo.controller;

import com.example.demo.dto.CottageDTO;
import com.example.demo.dto.RoomDTO;
import com.example.demo.model.*;
import com.example.demo.service.CottageOwnerService;
import com.example.demo.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @ResponseBody
    @RequestMapping(path = "/addCottage", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CottageDTO> saveCottage(@RequestBody CottageDTO cottageDTO) {
        addOwner();
        Cottage cottage = new Cottage();
        setAttributes(cottage, cottageDTO);
        cottage = cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(path = "/updateCottage", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CottageDTO> updateCottage(@RequestBody CottageDTO cottageDTO) {
        addOwner();
        addCottage();
        Cottage cottage = cottageService.findOne(cottageDTO.getId());
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        setAttributes(cottage, cottageDTO);
        cottage = cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/updateCottageImages", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CottageDTO> updateCottageImages(@RequestBody CottageDTO cottageDTO) {
        addOwner();
        addCottage();
        Cottage cottage = cottageService.findOne(cottageDTO.getId());
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Image> images = new ArrayList<>();
        for (String path : cottageDTO.getImagePaths())
            images.add(new Image(path));
        cottage.setImages(images);
        cottageService.save(cottage);
        return new ResponseEntity<>(cottageDTO, HttpStatus.OK);
    }


    private void setAttributes(Cottage cottage, CottageDTO cottageDTO) {
        cottage.setId(cottageDTO.getId());
        cottage.setName(cottageDTO.getName());
        cottage.setDescription(cottageDTO.getDescription());
        cottage.setAddress(new Address(cottageDTO.getAddress().getStreet(), cottageDTO.getAddress().getCity(), cottageDTO.getAddress().getCountry()));
        cottage.setPriceList(cottageDTO.getPrice());
        List<Rule> rules = new ArrayList<>();
        for (String ruleText : cottageDTO.getRules()) rules.add(new Rule(ruleText));
        cottage.setRules(rules);
        cottage.setAdditionalInfo(cottageDTO.getAdditionalInfo());
        List<Room> rooms = new ArrayList<>();
        for (RoomDTO dto : cottageDTO.getRooms()) {
            Room room = new Room();
            room.setNumberOfBeds(dto.getNumberOfBeds());
            rooms.add(room);
        }
        cottage.setRooms(rooms);
        List<Image> images = new ArrayList<>();
        for (String imgPath : cottageDTO.getImagePaths()) images.add(new Image(imgPath));
        cottage.setImages(images);
        CottageOwner owner = cottageOwnerService.findOne(cottageDTO.getOwnerId());
        if (owner != null) cottage.setOwner(owner);
    }

    private void addOwner() {
        CottageOwner owner = new CottageOwner("email@gmail.com", "pass", "Pero", "Peric",
                "12334556", new Address("Milana Rakica 19", "Novi Sad", "Srbija"));
        cottageOwnerService.save(owner);
    }

    private void addCottage() {
        Cottage cottage = new Cottage();
        cottage.setName("Vikendica");
        cottage.setDescription("Ovo je vikendica");
        cottage.setAddress(new Address("Marka Pola 12", "Novi Sad", "Srbija"));
        cottage.setPriceList(new BigDecimal(300));
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule("No smoking"));
        rules.add(new Rule("No drinking"));
        cottage.setRules(rules);
        cottage.setAdditionalInfo("This is my additional info");
        List<Room> rooms = new ArrayList<>();
        Room room = new Room();
        room.setNumberOfBeds(3);
        rooms.add(room);
        cottage.setRooms(rooms);
        List<Image> images = new ArrayList<>();
        cottage.setImages(images);
        CottageOwner owner = cottageOwnerService.findOne(1);
        if (owner != null) cottage.setOwner(owner);
        cottageService.save(cottage);
    }
}
