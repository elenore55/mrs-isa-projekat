package com.example.demo.service;

import com.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<String> getCities() {
        return repository.getCities();
    }

    public List<String> getCountries() {
        return repository.getCountries();
    }
}
