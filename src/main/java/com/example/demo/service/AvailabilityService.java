package com.example.demo.service;

import com.example.demo.model.Availability;
import com.example.demo.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {
    @Autowired
    private AvailabilityRepository availabilityRepository;

    public Availability findOne(Integer id){
        return availabilityRepository.getById(id);
    }

    public List<Availability> findAll(){
        return availabilityRepository.findAll();
    }

    public Availability save(Availability availability)
    {
        return availabilityRepository.save(availability);
    }

    public void remove(Integer id)
    {
        availabilityRepository.deleteById(id);
    }
}
