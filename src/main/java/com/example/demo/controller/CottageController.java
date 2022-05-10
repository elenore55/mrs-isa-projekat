package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.service.CottageOwnerService;
import com.example.demo.service.CottageService;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        cottage.getImages();
        cottage.getRules();
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getCottageImages/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<String>> getCottageImages(@PathVariable Integer id) {
        Cottage cottage = cottageService.findOne(id);
        if (cottage == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<String> result = new ArrayList<>();
        for (Image img : cottage.getImages()) {
            result.add(img.getPath());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
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
    @RequestMapping(path = "/detailViewCottage/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> detailViewCottage(@PathVariable Integer id) {
        Cottage cottage = cottageService.findOne(id);

        return new ResponseEntity<>(HttpStatus.OK);
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

    @ResponseBody
    @RequestMapping(path = "/getCottages", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CottageDTO>> getCottages() {
        List<Cottage> cottages = cottageService.getCottages();
        List<CottageDTO> dtos = new ArrayList<>();
        for (Cottage c : cottages) {
            dtos.add(new CottageDTO(c));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getCottagesWithRate", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CottageWithRateDTO>> getCottagesWithRate() {
        List<Cottage> cottages = cottageService.getCottages();
        List<CottageWithRateDTO> dtos = new ArrayList<>();
        for (Cottage c : cottages) {
            dtos.add(new CottageWithRateDTO(c));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/filter", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<CottageDTO>> filterCottages(@RequestBody UserFilterDTO userFilterDTO) {
        List<Cottage> cottages = cottageService.filter(userFilterDTO);
        List<CottageDTO> dtos = new ArrayList<>();
        System.out.println("######################################## Sortirnje" + userFilterDTO.getSortBy());

        for (Cottage c : cottages) {
            dtos.add(new CottageDTO(c));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getFastReservations/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<FastReservationDTO>> getFastReservations(@PathVariable Integer id) {
        Cottage c = cottageService.findOne(id);
        if (c == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<FastReservationDTO> result = new ArrayList<>();
        for (FastReservation fr : c.getFastReservations()) {
            result.add(new FastReservationDTO(fr));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
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
