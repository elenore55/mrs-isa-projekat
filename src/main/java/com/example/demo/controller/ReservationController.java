package com.example.demo.controller;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.model.Client;
import com.example.demo.model.Offer;
import com.example.demo.model.Reservation;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.model.FishingInstructor;
import com.example.demo.service.FishingInstructorService;
import com.example.demo.service.OfferService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "api/reservations")
public class ReservationController {
    private ReservationService reservationService;
    private UserService userService;
    private OfferService offerService;
    private FishingInstructorService fishingInstructorService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService, OfferService offerService,FishingInstructorService fishingInstructorService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.offerService = offerService;
        this.fishingInstructorService = fishingInstructorService;

    }

    @ResponseBody
    @RequestMapping(path = "/addReservation", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody ReservationDTO dto) {
        Reservation reservation = new Reservation();
        setAttributes(reservation, dto);
        if (reservation.getClient() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!reservationService.isPossibleReservation(reservation))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        reservation.setReservationStatus(ReservationStatus.PENDING);
        reservation = reservationService.save(reservation);
        userService.addReservation(dto.getOwnerId(), reservation);
        reservationService.notifyClient(reservation);
        return new ResponseEntity<>(new ReservationDTO(reservation), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/confirmReservation/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> confirmReservation(@PathVariable Integer id) {
        Reservation reservation = reservationService.findOne(id);
        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (reservation.getReservationStatus() == ReservationStatus.PENDING) {
            reservation.setReservationStatus(ReservationStatus.ACTIVE);
            reservationService.save(reservation);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void setAttributes(Reservation r, ReservationDTO dto) {
        r.setId(dto.getId());
        r.setStart(dto.getStartDate());
        r.setEnd(dto.getEndDate());
        Offer offer = offerService.findOne(dto.getOfferId());
        r.setOffer(offer);
        Client client = userService.findClientByEmail(dto.getClientEmail());
        r.setClient(client);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {

        List<Reservation> reservations = reservationService.findAll();
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationDTOS.add(new ReservationDTO(reservation));
        }

        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/advreser/{id1}/{id2}")
    public ResponseEntity<List<ReservationDTO>> getAdventuresReservations(@PathVariable Integer id1,@PathVariable Integer id2) {

        FishingInstructor fishingInstructor = fishingInstructorService.findOne(1);
        List<Reservation> reservations = fishingInstructor.getReservations();

        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if(reservation.getOffer().getId().equals(1))
                    reservationDTOS.add(new ReservationDTO(reservation));
        }

        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/allBetween")
    public ResponseEntity<List<ReservationDTO>> getAllBetweenReservations(LocalDateTime start, LocalDateTime end) {

        List<Reservation> reservations = reservationService.findAll();
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getStart().isAfter(start) && reservation.getEnd().isBefore(end))
                reservationDTOS.add(new ReservationDTO(reservation));
        }

        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

}
