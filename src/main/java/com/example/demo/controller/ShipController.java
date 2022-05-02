package com.example.demo.controller;

import com.example.demo.dto.FishingEquipmentDTO;
import com.example.demo.dto.NavigationEquipmentDTO;
import com.example.demo.dto.ShipDTO;
import com.example.demo.model.*;
import com.example.demo.model.enums.ShipType;
import com.example.demo.service.AdventureService;
import com.example.demo.service.ShipOwnerService;
import com.example.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/ships")
public class ShipController {
    private ShipService shipService;
    private ShipOwnerService shipOwnerService;

    @Autowired
    public ShipController(ShipService shipService, ShipOwnerService shipOwnerService) {
        this.shipService = shipService;
        this.shipOwnerService = shipOwnerService;
    }

    @ResponseBody
    @RequestMapping(path = "/addShip", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ShipDTO> saveShip(@RequestBody ShipDTO shipDTO) {
        Ship ship = new Ship();
        setAttributes(ship, shipDTO);
        ship = shipService.save(ship);
        return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(path = "/updateShip", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ShipDTO> updateShip(@RequestBody ShipDTO shipDTO) {
        Ship ship = shipService.findOne(shipDTO.getId());
        if (ship == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        setAttributes(ship, shipDTO);
        ship = shipService.save(ship);
        return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/updateShipImages", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ShipDTO> updateShipImages(@RequestBody ShipDTO shipDTO) {
        Ship ship = shipService.findOne(shipDTO.getId());
        if (ship == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Image> images = new ArrayList<>();
        for (String path : shipDTO.getImagePaths())
            images.add(new Image(path));
        ship.setImages(images);
        shipService.save(ship);
        return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/deleteShip/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteShip(@PathVariable Integer id) {
        Ship ship = shipService.findOne(id);
        if (ship == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!shipService.checkReservations(ship))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        shipService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void setAttributes(Ship ship, ShipDTO dto) {
        ship.setId(dto.getId());
        ship.setName(dto.getName());
        ship.setDescription(dto.getDescription());
        ship.setPriceList(dto.getPrice());
        ship.setAdditionalInfo(dto.getAdditionalInfo());
        ship.setLength(dto.getLength());
        ship.setCancellationConditions(dto.getCancellationConditions());
        ship.setCapacity(dto.getCapacity());
        ship.setNumberOfEngines(dto.getNumberOfEngines());
        ship.setPowerOfEngine(dto.getPowerOfEngine());
        ship.setShipType(ShipType.values()[dto.getShipType() - 1]);
        ship.setMaxSpeed(dto.getMaxSpeed());
        // address
        ship.setAddress(new Address(dto.getAddress().getStreet(), dto.getAddress().getCity(), dto.getAddress().getCountry()));
        // navigation eq
        List<NavigationEquipment> navigationEquipmentList = new ArrayList<>();
        for (NavigationEquipmentDTO n : dto.getNavigationEquipmentList())
            navigationEquipmentList.add(new NavigationEquipment(n.getName(), n.getAmount()));
        ship.setNavigationEquipmentList(navigationEquipmentList);
        // fishing eq
        List<FishingEquipment> fishingEquipmentList = new ArrayList<>();
        for (FishingEquipmentDTO f : dto.getFishingEquipmentList())
            fishingEquipmentList.add(new FishingEquipment(f.getName(), f.getAmount()));
        ship.setFishingEquipmentList(fishingEquipmentList);
        // rules
        List<Rule> rules = new ArrayList<>();
        for (String ruleText : dto.getRules()) rules.add(new Rule(ruleText));
        ship.setRules(rules);
        // images
        List<Image> images = new ArrayList<>();
        for (String imgPath : dto.getImagePaths()) images.add(new Image(imgPath));
        ship.setImages(images);
        // owner
        ShipOwner owner = shipOwnerService.findOne(dto.getOwnerId());
        if (owner != null) ship.setOwner(owner);
    }
}
