package com.example.demo.service;

import com.example.demo.model.ShipOwner;
import com.example.demo.repository.ShipOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipOwnerService {
    private ShipOwnerRepository shipOwnerRepository;

    @Autowired
    public ShipOwnerService(ShipOwnerRepository shipOwnerRepository) {
        this.shipOwnerRepository = shipOwnerRepository;
    }

    public ShipOwner findOne(Integer id) {
        return shipOwnerRepository.findById(id).orElseGet(null);
    }

    public ShipOwner save(ShipOwner shipOwner) {
        return this.shipOwnerRepository.save(shipOwner);
    }
}
