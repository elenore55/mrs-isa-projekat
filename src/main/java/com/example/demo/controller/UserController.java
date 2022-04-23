package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}
