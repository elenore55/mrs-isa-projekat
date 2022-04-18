package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/client")
public class ClientController {
    private ClientService clientService;


@Autowired
public ClientController (ClientService clientService)
{
    this.clientService = clientService;
}

@ResponseBody
@RequestMapping(path="/registration", method = RequestMethod.POST, consumes = "aplication/json")
public ResponseEntity<RegistrationDTO> saveClient(@RequestBody RegistrationDTO registrationDTO){
    Client c= new Client(registrationDTO);
    c = clientService.save(c);
    System.out.println("Bila sam u ovom nekom dodavanju");
    return new ResponseEntity<>(new RegistrationDTO(c), HttpStatus.CREATED);
}

@RequestMapping(path="/login", method = RequestMethod.POST, consumes = "aplication/json")
public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO){
    Client c = clientService.findOneByEmail(loginDTO.getEmail());


}


}
