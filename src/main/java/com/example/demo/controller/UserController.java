package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(path = "/registration", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<RegistrationDTO> saveUser(@RequestBody RegistrationDTO registrationDTO){


        User u = new User(registrationDTO);
        // boolean exists = userService.isAlreadyRegistered(registrationDTO.getEmail());
        u = userService.save(u);
        return new ResponseEntity<>(new RegistrationDTO(u), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(path = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<LoginDTO> login_user(@RequestBody LoginDTO loginDTO){
        boolean exist = userService.checkIfExists(loginDTO.getEmail(), loginDTO.getPassword());
        return new ResponseEntity<>(loginDTO, HttpStatus.OK);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(path = "/getOwnersReservations/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ReservationDTO>> getOwnersReservations(@PathVariable Integer id) {
        User user = userService.findOne(id);
        if (!(user instanceof CottageOwner) && !(user instanceof ShipOwner) && !(user instanceof FishingInstructor)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Reservation> reservations;
        List<ReservationDTO> result = new ArrayList<>();
        if (user instanceof CottageOwner) reservations = ((CottageOwner)user).getReservations();
        else if (user instanceof ShipOwner) reservations = ((ShipOwner)user).getReservations();
        else reservations = ((FishingInstructor)user).getReservations();
        for (Reservation r : reservations) {
            setReservationStatus(r);
            result.add(new ReservationDTO(r));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private void setReservationStatus(Reservation r) {
        if (r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED || r.getReservationStatus() == ReservationStatus.CANCELLED) return;
        LocalDateTime today = LocalDateTime.now();
        if (r.getEnd().compareTo(today) < 0) r.setReservationStatus(ReservationStatus.FINISHED);
        else if (r.getStart().compareTo(today) > 0) r.setReservationStatus(ReservationStatus.PENDING);
        else r.setReservationStatus(ReservationStatus.ACTIVE);
    }

    @GetMapping(value = "/allUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<User> users = userService.findAll();


        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(user));
        }

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }



}
