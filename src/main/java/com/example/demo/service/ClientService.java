package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client findOne(Integer id){ return clientRepository.getById(id);}
    public List<Client> findAll() {return clientRepository.findAll();}
    public Client save(Client client){
        return clientRepository.save(client);
    }

    public void remove(Integer id) { clientRepository.deleteById(id);}
}
