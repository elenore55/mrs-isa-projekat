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
        CottageOwner owner = new CottageOwner("email@gmail.com", "pass", "Pero", "Peric",
                "12334556", new Address("Milana Rakica 19", "Novi Sad", "Srbija"));
        cottageOwnerService.save(owner);
        Cottage cottage = new Cottage();
        setAttributes(cottage, cottageDTO);
        cottage = cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(path = "/updateCottage", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CottageDTO> updateCottage(@RequestBody CottageDTO cottageDTO) {
        Cottage cottage = cottageService.findOne(cottageDTO.getId());
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        setAttributes(cottage, cottageDTO);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
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
}
