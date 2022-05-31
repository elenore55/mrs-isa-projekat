package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.model.enums.Category;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.Profile_DataRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private Profile_DataRepository profileDataRepository;

    @Autowired
    public UserService(UserRepository userRepository, ClientRepository clientRepository, Profile_DataRepository profileDataRepository)
    {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.profileDataRepository = profileDataRepository;
    }

    public User save(User user){
        Client c = new Client();

        c.setProfileData(user.getProfileData());
        c.setNumberOfPoints(0);
        c.setCategory(Category.REGULAR);
        Client retC = clientRepository.save(c);
        return retC;
    }

    public Client findClientByEmail(String email) {
        ProfileData pd = profileDataRepository.getByEmail(email);
        if (pd == null) return null;
        return clientRepository.findByProfileDataId(pd.getId());
    }

    public boolean checkIfExists(String email, String password) {
        //List<User> us = userRepository.findByEmail(email);

        //User u = new User();
        //if (u.getPassword().equals(password))
        //    return true;
        return false;
    }

    /*public boolean isAlreadyRegistered(String email) {
        if (userRepository.isAlreadyRegistered(email))
        return false;
    }*/

    public User findOne(Integer id) {
        return userRepository.findById(id).orElseGet(null);
    }

    public void addReservation(Integer id, Reservation reservation) {
        User user = findOne(id);
        if (user instanceof CottageOwner) {
            CottageOwner c = (CottageOwner)user;
            List<Reservation> reservations = c.getReservations();
            reservations.add(reservation);
            c.setReservations(reservations);
            userRepository.save(c);
        } else if (user instanceof ShipOwner) {
            ShipOwner c = (ShipOwner)user;
            List<Reservation> reservations = c.getReservations();
            reservations.add(reservation);
            c.setReservations(reservations);
            userRepository.save(c);
        } else if (user instanceof FishingInstructor) {
            FishingInstructor fi = (FishingInstructor) user;
            List<Reservation> reservations = fi.getReservations();
            reservations.add(reservation);
            fi.setReservations(reservations);
            userRepository.save(fi);
        }
    }
}
