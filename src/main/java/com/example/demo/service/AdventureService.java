package com.example.demo.service;

import com.example.demo.model.Adventure;
import com.example.demo.model.FishingEquipment;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.AdventureRepository;
import com.example.demo.repository.Adventure_Fishing_Equipments_Repository;
import com.example.demo.repository.FishingEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdventureService {
    @Autowired
    private AdventureRepository adventureRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private FishingEquipmentRepository fishingEquipmentRepository;

    public Adventure findOne(Integer id) {
        return adventureRepository.getById(id);
    }

    public List<Adventure> findAll(){
        return  adventureRepository.findAll();
    }

    public Adventure save(Adventure adventure) {
        return adventureRepository.save(adventure);
    }

    public Adventure update(Adventure adventure){
        return adventureRepository.save(adventure);
    }

    public void remove(Integer id) {
        adventureRepository.deleteById(id);
    }
}
