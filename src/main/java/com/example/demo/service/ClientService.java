package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository client_repository){
        this.clientRepository = client_repository;
    }

    public Client save(Client client){
        return clientRepository.save(client);
    }

    public Client findOneByEmail(String email) {
        return clientRepository.findOneByEmail(email).orElseGet(null);

    }
}
