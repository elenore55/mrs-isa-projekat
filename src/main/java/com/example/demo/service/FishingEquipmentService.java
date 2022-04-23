package com.example.demo.service;

import com.example.demo.model.FishingEquipment;
import com.example.demo.repository.FishingEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishingEquipmentService {
    @Autowired
    private FishingEquipmentRepository fishingEquipmentRepository;

    public FishingEquipment findOne(Integer id){
        return fishingEquipmentRepository.getById(id);
    }

    public List<FishingEquipment> findAll(){
        return fishingEquipmentRepository.findAll();
    }

}
