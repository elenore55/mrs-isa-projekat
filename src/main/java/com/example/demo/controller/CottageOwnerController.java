package com.example.demo.controller;

import com.example.demo.dto.CottageDTO;
import com.example.demo.dto.FilterCottageDTO;
import com.example.demo.dto.comparators.CottageNameComparator;
import com.example.demo.dto.comparators.CottagePriceComparator;
import com.example.demo.dto.comparators.CottageRatingComparator;
import com.example.demo.dto.comparators.CottageRoomsComparator;
import com.example.demo.model.Address;
import com.example.demo.model.Cottage;
import com.example.demo.model.CottageOwner;
import com.example.demo.service.CottageOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "api/cottageOwner")
public class CottageOwnerController {
    private CottageOwnerService service;

    @Autowired
    public CottageOwnerController(CottageOwnerService service) {
        this.service = service;
    }


    @ResponseBody
    @RequestMapping(path = "/getCottages/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CottageDTO>> getCottages(@PathVariable Integer id) {
        CottageOwner owner = service.findOne(id);
        List<Cottage> cottages = owner.getCottages();
        List<CottageDTO> dtos = new ArrayList<>();
        for (Cottage c : cottages) {
            dtos.add(new CottageDTO(c));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getCottages/{id}/{search}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CottageDTO>> getCottages(@PathVariable Integer id, @PathVariable String search) {
        search = search.toLowerCase();
        CottageOwner owner = service.findOne(id);
        List<Cottage> cottages = owner.getCottages();
        List<CottageDTO> dtos = new ArrayList<>();
        for (Cottage c : cottages) {
            Address a = c.getAddress();
            if (c.getName().toLowerCase().contains(search) || c.getDescription().toLowerCase().contains(search) ||  c.getAdditionalInfo().toLowerCase().contains(search) ||
                a.getStreet().toLowerCase().contains(search) || a.getCity().toLowerCase().contains(search) || a.getCountry().toLowerCase().contains(search)) {
                dtos.add(new CottageDTO(c));
            }
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/filterCottages/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<CottageDTO>> filterCottages(@PathVariable Integer id, @RequestBody FilterCottageDTO filter) {
        CottageOwner owner = service.findOne(id);
        List<Cottage> cottages = owner.getCottages();
        List<CottageDTO> dtos = new ArrayList<>();
        for (Cottage c : cottages) {
            Address a = c.getAddress();
            if (filter.checkCity(a.getCity()) && filter.checkCountry(a.getCountry()) && filter.checkPrice(c.getPriceList())) {
                dtos.add(new CottageDTO(c));
            }
        }
        boolean reverse = !filter.getSortDir().equalsIgnoreCase("ascending");
        Comparator<CottageDTO> comparator;
        if (filter.getSortParam().equalsIgnoreCase("number of rooms")) comparator = new CottageRoomsComparator();
        else if (filter.getSortParam().equalsIgnoreCase("price")) comparator = new CottagePriceComparator();
        else if (filter.getSortParam().equalsIgnoreCase("rating")) comparator = new CottageRatingComparator();
        else comparator = new CottageNameComparator();
        if (reverse) dtos.sort(Collections.reverseOrder(comparator));
        else dtos.sort(comparator);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
