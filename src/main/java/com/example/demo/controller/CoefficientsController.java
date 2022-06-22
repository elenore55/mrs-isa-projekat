package com.example.demo.controller;

import com.example.demo.dto.AdventureDTO;
import com.example.demo.dto.CoefDTO;
import com.example.demo.model.Adventure;
import com.example.demo.model.Coefficients;
import com.example.demo.service.CoefficientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/coefficients")
public class CoefficientsController {
    private CoefficientsService coefficientsService;

    @Autowired
    public CoefficientsController(CoefficientsService coefficientsService) {
        this.coefficientsService = coefficientsService;
    }

    @ResponseBody
    @RequestMapping(path = "/updateCoeffs",method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CoefDTO> updateInstructorInfo(@RequestBody CoefDTO coefDTO){
        Coefficients coefficients = coefficientsService.findOne(1);
        coefficients.setOwnerReservationPoints(coefDTO.getOwnerReservationPoints());
        coefficients.setPercentageClientGold(coefDTO.getPercentageClientGold());
        coefficients.setPercentageClientSilver(coefDTO.getPercentageClientSilver());
        coefficients.setPercentageOwnerGold(coefDTO.getPercentageOwnerGold());
        coefficients.setPercentageOwnerSilver(coefDTO.getPercentageOwnerSilver());
        coefficients.setRequiredPointsGold(coefDTO.getRequiredPointsGold());
        coefficients.setRequiredPointsSilver(coefDTO.getRequiredPointsSilver());
        coefficients.setReservationPercentage(coefDTO.getReservationPercentage());
        coefficients.setUserReservationPoints(coefDTO.getUserReservationPoints());

        coefficients = coefficientsService.save(coefficients);
        return new ResponseEntity<>(new CoefDTO(coefficients), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/getCoefs")
    public ResponseEntity<CoefDTO> getAdventure() {

        Coefficients coefficients = coefficientsService.findOne(1);
        if (coefficients == null) {
            // nije deklarisano i traba dodati u bazi prvobitno
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CoefDTO(coefficients), HttpStatus.OK);
    }
}
