package com.example.demo.controller;

import com.example.demo.dto.CottageDTO;
import com.example.demo.dto.RoomDTO;
import com.example.demo.model.*;
import com.example.demo.service.CottageOwnerService;
import com.example.demo.service.CottageService;
import com.example.demo.service.RoomService;
import org.apache.catalina.mbeans.SparseUserDatabaseMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/cottages")
public class CottageController {
    private CottageService cottageService;
    private CottageOwnerService cottageOwnerService;
    private RoomService roomService;

    @Autowired
    public CottageController(CottageService cottageService, CottageOwnerService cottageOwnerService, RoomService roomService) {
        this.cottageService = cottageService;
        this.cottageOwnerService = cottageOwnerService;
        this.roomService = roomService;
    }

    @ResponseBody
    @RequestMapping(path = "/getCottage/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CottageDTO> getCottage(@PathVariable Integer id) {
        Cottage cottage = cottageService.findOne(id);
        if (cottage == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/addCottage", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CottageDTO> saveCottage(@RequestBody CottageDTO cottageDTO) {
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
        cottage = cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/updateCottageImages", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CottageDTO> updateCottageImages(@RequestBody CottageDTO cottageDTO) {
        Cottage cottage = cottageService.findOne(cottageDTO.getId());
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Image> images = new ArrayList<>();
        for (String path : cottageDTO.getImagePaths())
            images.add(new Image(path));
        cottage.setImages(images);
        cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/updateReservationPeriod", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CottageDTO> updateReservationPeriod(@RequestBody CottageDTO cottageDTO) {
        Cottage cottage = cottageService.findOne(cottageDTO.getId());
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Availability a = new Availability(cottageDTO.getAvailableStart(), cottageDTO.getAvailableEnd());
        a.setOffer(cottage);
        cottage.setAvailabilities(Arrays.asList(a));
        cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/deleteCottage/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCottage(@PathVariable Integer id) {
        Cottage cottage = cottageService.findOne(id);
        if (cottage == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!cottageService.checkReservations(cottage))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        cottageService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
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
        roomService.deleteRooms(cottageDTO.getId());
        for (RoomDTO dto : cottageDTO.getRooms()) {
            Room room = new Room();
            room.setCottage(cottage);
            room.setNumberOfBeds(dto.getNumberOfBeds());
            rooms.add(room);
        }
        cottage.setRooms(rooms);
        List<Image> images = new ArrayList<>();
        for (String imgPath : cottageDTO.getImagePaths()) images.add(new Image(imgPath));
        cottage.setImages(images);
        if (cottageDTO.getAvailableStart() != null && cottageDTO.getAvailableEnd() != null) {
            Availability a = new Availability(cottageDTO.getAvailableStart(), cottageDTO.getAvailableStart());
            a.setOffer(cottage);
            cottage.setAvailabilities(Arrays.asList(a));
        }
        CottageOwner owner = cottageOwnerService.findOne(cottageDTO.getOwnerId());
        if (owner != null) cottage.setOwner(owner);
    }
}
