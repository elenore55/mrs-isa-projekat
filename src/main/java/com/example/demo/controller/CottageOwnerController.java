package com.example.demo.controller;

import com.example.demo.dto.CottageDTO;
import com.example.demo.model.Cottage;
import com.example.demo.model.CottageOwner;
import com.example.demo.service.CottageOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
}
