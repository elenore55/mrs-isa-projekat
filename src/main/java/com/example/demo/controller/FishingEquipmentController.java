package com.example.demo.controller;

import com.example.demo.dto.FishingEquipmentDTO;
import com.example.demo.model.FishingEquipment;
import com.example.demo.service.FishingEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/fishingEquipment")
public class FishingEquipmentController {

    private FishingEquipmentService fishingEquipmentService;

    @Autowired
    public FishingEquipmentController(FishingEquipmentService fishingEquipmentService)
    {
        this.fishingEquipmentService=fishingEquipmentService;
    }

    @GetMapping(value="/all")
    public ResponseEntity<List<FishingEquipmentDTO>> getAllFishingEquipment(){
        List<FishingEquipment> fishingEquipmentList = fishingEquipmentService.findAll();
        List<FishingEquipmentDTO> fishingEquipmentDTOList = new ArrayList<>();
        for (FishingEquipment fishingEquipment : fishingEquipmentList){
            fishingEquipmentDTOList.add(new FishingEquipmentDTO(fishingEquipment));
        }
        return new ResponseEntity<>(fishingEquipmentDTOList, HttpStatus.OK);
    }
}
