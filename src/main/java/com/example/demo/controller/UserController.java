package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.dto.comparators.reservation.ReservationDateComparator;
import com.example.demo.dto.comparators.reservation.ReservationOfferComparator;
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
import java.util.Collections;
import java.util.Comparator;
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
        if (user instanceof CottageOwner) reservations = ((CottageOwner) user).getReservations();
        else reservations = ((ShipOwner) user).getReservations();
        for (Reservation r : reservations) {
            setReservationStatus(r);
            result.add(new ReservationDTO(r));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(path = "/getOwnersReservations/{id}/{offerId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ReservationDTO>> getOwnersReservations(@PathVariable Integer id, @PathVariable Integer offerId) {
        User user = userService.findOne(id);
        if (!(user instanceof CottageOwner) && !(user instanceof ShipOwner)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Reservation> reservations;
        if (user instanceof CottageOwner) reservations = ((CottageOwner) user).getReservations();
        else reservations = ((ShipOwner) user).getReservations();
        List<ReservationDTO> result = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getOffer().getId().equals(offerId)) {
                setReservationStatus(r);
                result.add(new ReservationDTO(r));
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(path = "/getFilteredOwnersReservations/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<ReservationDTO>> getOwnersReservations(@PathVariable Integer id, @RequestBody ReservationsFilterDTO dto) {
        User user = userService.findOne(id);
        if (!(user instanceof CottageOwner) && !(user instanceof ShipOwner)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Reservation> reservations;
        List<ReservationDTO> result = new ArrayList<>();
        if (user instanceof CottageOwner) reservations = ((CottageOwner) user).getReservations();
        else reservations = ((ShipOwner) user).getReservations();
        sortReservations(reservations, dto.getSortBy(), dto.getDesc());
        for (Reservation r : reservations) {
            if (r.getStart().compareTo(dto.getStartDate()) >= 0 && r.getEnd().compareTo(dto.getEndDate()) <= 0) {
                setReservationStatus(r);
                result.add(new ReservationDTO(r));
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private void sortReservations(List<Reservation> reservations, String sortBy, boolean desc) {
        Comparator<Reservation> comparator;
        if (sortBy.equalsIgnoreCase("Date")) comparator = new ReservationDateComparator();
        else comparator = new ReservationOfferComparator();
        if (desc) reservations.sort(Collections.reverseOrder(comparator));
        else reservations.sort(comparator);
    }

    private void setReservationStatus(Reservation r) {
        if (r.getReservationStatus() == ReservationStatus.CLIENT_NOT_ARRIVED || r.getReservationStatus() == ReservationStatus.CANCELLED)
            return;
        LocalDateTime today = LocalDateTime.now();
        if (r.getEnd().compareTo(today) < 0) r.setReservationStatus(ReservationStatus.FINISHED);
        else if (r.getStart().compareTo(today) > 0) r.setReservationStatus(ReservationStatus.PENDING);
        else r.setReservationStatus(ReservationStatus.ACTIVE);
    }
}
