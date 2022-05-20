package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(path = "/registration", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> saveUser(@RequestBody RegistrationDTO registrationDTO) {
        boolean exists = userService.isAlreadyRegistered(registrationDTO.getEmail());
        if (exists) {
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        User u = new User(registrationDTO);
        userService.save(u);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(path = "/login", method = RequestMethod.POST, consumes = "application/json")
    public String login_user(@RequestBody LoginDTO loginDTO) {
        String token = userService.findUserToken(loginDTO.getEmail(), loginDTO.getPassword());
        System.out.println("Trenutni token je " + token);
        return token;
    }

    @Transactional
    @ResponseBody
    @RequestMapping(path = "/edit", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> saveRequest(@RequestBody EditProfileDTO editProfileDTO) {
        userService.updateProfileData(editProfileDTO);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(path = "/changePassword", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        if (userService.isUsersPassword(changePasswordDTO.getOld(), changePasswordDTO.getId())) {
            System.out.println("Old password je dobro unesena");
            userService.changePassword(changePasswordDTO);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>("ERROR", HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getOwner/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserDTO> getOwner(@PathVariable Integer id) {
        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!(user instanceof CottageOwner) && !(user instanceof ShipOwner)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(path = "/updateUser/{id}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO dto) {
        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhoneNumber(dto.getPhoneNumber());
        Address a = new Address(dto.getAddress().getStreet(), dto.getAddress().getCity(), dto.getAddress().getCountry());
        user.setAddress(a);
        user = userService.save(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(path = "/getOwnersReservations/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ReservationDTO>> getOwnersReservations(@PathVariable Integer id) {
        User user = userService.findOne(id);
        if (!(user instanceof CottageOwner) && !(user instanceof ShipOwner)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Reservation> reservations;
        List<ReservationDTO> result = new ArrayList<>();
        if (user instanceof CottageOwner) reservations = ((CottageOwner)user).getReservations();
        else reservations = ((ShipOwner)user).getReservations();
        for (Reservation r : reservations) {
            result.add(new ReservationDTO(r));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
