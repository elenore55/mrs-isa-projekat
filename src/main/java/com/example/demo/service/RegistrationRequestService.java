package com.example.demo.service;

import com.example.demo.model.RegistrationRequest;
import com.example.demo.repository.RegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationRequestService {
    private RegistrationRequestRepository repository;

    @Autowired
    public RegistrationRequestService(RegistrationRequestRepository repository) {
        this.repository = repository;
    }

    public RegistrationRequest save(RegistrationRequest request) {
        return repository.save(request);
    }
}
