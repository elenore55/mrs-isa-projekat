package com.example.demo.service;

import com.example.demo.model.Adventure;
import com.example.demo.repository.AdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureService {
    @Autowired
    private AdventureRepository adventureRepository;

    public Adventure findOne(Integer id) {
        return adventureRepository.getById(id);
    }

    public List<Adventure> findAll(){
        return  adventureRepository.findAll();
    }

    public Adventure save(Adventure adventure) {
        return adventureRepository.save(adventure);
    }

    public void remove(Integer id) {
        adventureRepository.deleteById(id);
    }
}
