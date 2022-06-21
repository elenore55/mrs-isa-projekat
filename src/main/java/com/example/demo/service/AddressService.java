package com.example.demo.service;

import com.example.demo.model.Address;
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

    public Address save(Address address) {
        return repository.save(address);
    }

    public Address getAddress(Address dto) {
        Address address = repository.getExistingAddress(dto.getStreet(), dto.getCity(), dto.getCountry());
        if (address == null) return save(dto);
        return address;
    }
}
