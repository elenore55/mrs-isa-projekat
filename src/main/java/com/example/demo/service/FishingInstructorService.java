package com.example.demo.service;

import com.example.demo.model.Adventure;
import com.example.demo.model.FishingInstructor;
import com.example.demo.repository.FishingInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishingInstructorService {

    @Autowired
    private FishingInstructorRepository fishingInstructorRepository;

    public FishingInstructor findOne(Integer id) {
        return fishingInstructorRepository.getById(id);
    }

    public List<FishingInstructor> findAll(){
        return  fishingInstructorRepository.findAll();
    }

    public FishingInstructor save(FishingInstructor fishingInstructor) {
        return fishingInstructorRepository.save(fishingInstructor);
    }

    public void remove(Integer id) {
        fishingInstructorRepository.deleteById(id);
    }
}
