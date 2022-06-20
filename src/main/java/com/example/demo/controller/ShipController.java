package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.model.enums.ShipType;
import com.example.demo.service.ShipOwnerService;
import com.example.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

    @ResponseBody
    @RequestMapping(path = "/getShip/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ShipDTO> getShip(@PathVariable Integer id) {
        Ship ship = shipService.findOne(id);
        if (ship == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ship.getImages();
        ship.getRules();
        return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getShips", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ShipDTO>> getShips() {
        List<Ship> ships = shipService.getShips();
        List<ShipDTO> dtos = new ArrayList<>();
        for (Ship s : ships) {
            dtos.add(new ShipDTO(s));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getShipsWithRate", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ShipWithRateDTO>> getShipsWithRate() {
        List<Ship> ships = shipService.getShips();
        List<ShipWithRateDTO> dtos = new ArrayList<>();
        for (Ship s : ships) {
            dtos.add(new ShipWithRateDTO(s));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getShipImages/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<String>> getShipImages(@PathVariable Integer id) {
        Ship ship = shipService.findOne(id);
        if (ship == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<String> result = new ArrayList<>();
        for (Image img : ship.getImages())
            result.add(img.getPath());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/filter", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<ShipDTO>> filterShips(@RequestBody UserFilterDTO userFilterDTO) {
        List<Ship> ships = shipService.filter(userFilterDTO);
        List<ShipDTO> dtos = new ArrayList<>();
        for (Ship s : ships) {
            dtos.add(new ShipDTO(s));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/updateReservationPeriod", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ShipDTO> updateReservationPeriod(@RequestBody ShipDTO dto) {
        Ship ship = shipService.findOne(dto.getId());
        if (ship == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Availability a = new Availability(dto.getAvailableStart(), dto.getAvailableEnd());
        a.setOffer(ship);
        ship.setAvailabilities(Arrays.asList(a));
        shipService.save(ship);
        return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.OK);
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
        Address address = ship.getAddress();
        if (address == null) address = new Address();
        address.setStreet(dto.getAddress().getStreet());
        address.setCity(dto.getAddress().getCity());
        address.setCountry(dto.getAddress().getCountry());
        ship.setAddress(address);
        // ship.setAddress(new Address(dto.getAddress().getStreet(), dto.getAddress().getCity(), dto.getAddress().getCountry()));
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
        // availabilities
        if (dto.getAvailableStart() != null && dto.getAvailableEnd() != null) {
            Availability a = new Availability(dto.getAvailableStart(), dto.getAvailableStart());
            a.setOffer(ship);
            ship.setAvailabilities(Arrays.asList(a));
        }

        // price history
        List<PriceList> priceHistory = new ArrayList<>();
        if (ship.getPriceHistory() == null || ship.getPriceHistory().size() == 0) {
            priceHistory.add(new PriceList(LocalDate.now(), dto.getPrice()));
        } else {
            for (PriceList pl : ship.getPriceHistory()) {
                PriceList p = new PriceList();
                p.setAmount(pl.getAmount());
                p.setEndDate(pl.getEndDate());
                p.setStartDate(pl.getStartDate());
                priceHistory.add(p);
            }
            PriceList last = priceHistory.get(priceHistory.size() - 1);
            if (!last.getAmount().equals(dto.getPrice())) {
                PriceList newPrice = new PriceList(LocalDate.now(), dto.getPrice());
                priceHistory.add(newPrice);
            }
        }
        ship.setPriceHistory(priceHistory);

        // owner
        ShipOwner owner = shipOwnerService.findOne(dto.getOwnerId());
        if (owner != null) ship.setOwner(owner);
    }
}
