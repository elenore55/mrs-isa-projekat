package com.example.demo.service;

import com.example.demo.model.Adventure;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.AdventureRepository;
import com.example.demo.repository.Adventure_Fishing_Equipments_Repository;
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
//    @Autowired
//    private Adventure_Fishing_Equipments_Repository adventure_fishing_equipments_repository;

    public Adventure findOne(Integer id) {
        return adventureRepository.getById(id);
    }

    public List<Adventure> findAll(){
        return  adventureRepository.findAll();
    }

    public Adventure save(Adventure adventure) {
        // adventure.getPriceList().setStartDate(LocalDate.now());
        // dodati proveru DA LI postoji poslata adresa I kako to spreciti da se doda ponovo
        //dventure_fishing_equipments_repository.save(adventure.getId(),adventure.getAddress().getId()); //KAKOOOOO!!!
        return adventureRepository.save(adventure);
    }

    public Adventure update(Adventure adventure){
        return adventureRepository.save(adventure);
    }

    public void remove(Integer id) {
        adventureRepository.deleteById(id);
    }
}
