package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.User;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private ClientRepository clientRepository;

    @Autowired
    public UserService(UserRepository userRepository, ClientRepository clientRepository)
    {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    public User save(User user){
        System.out.println("BILA SAM U SVOM SERVISU             BILA SAM U SVOM SERVISU             BILA SAM U SVOM SERVISU     ");
        Client c = new Client();
        c.setProfileData(user.getProfileData());

        return clientRepository.save(c);
    }

    public User findOneByEmail(String email)
    {
        //return userRepository.findOneByEmail(email);
        return new User();
    }

    public boolean checkIfExists(String email, String password) {
        //List<User> us = userRepository.findByEmail(email);

        //User u = new User();
        //if (u.getPassword().equals(password))
        //    return true;
        return false;
    }
}
