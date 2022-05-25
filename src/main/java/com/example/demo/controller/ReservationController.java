package com.example.demo.controller;

import com.example.demo.dto.CottageDTO;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.dto.ReservationDTOstring;
import com.example.demo.dto.ReservationExtendedDTO;
import com.example.demo.model.Client;
import com.example.demo.model.Cottage;
import com.example.demo.model.Offer;
import com.example.demo.model.Reservation;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.service.OfferService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/reservations")
public class ReservationController {
    private ReservationService reservationService;
    private UserService userService;
    private OfferService offerService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService, OfferService offerService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.offerService = offerService;
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
        reservationService.notifyClient(reservation);
        return new ResponseEntity<>(new ReservationDTO(reservation), HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(path = "/addReservationStringDate", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> addReservationStringDate(@RequestBody ReservationDTOstring dto) {
        Reservation r = new Reservation();
        setAttributes(r, dto);
        r = reservationService.save(r);
        reservationService.notifyClient(r);
        return new ResponseEntity<>("OK", HttpStatus.OK);
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

    @ResponseBody
    @RequestMapping(path = "/getClientUpcomingReservations/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ReservationExtendedDTO>> getClientsUpcomingReservations(@PathVariable Integer id) {
        List<Reservation> reservations = reservationService.getClientsUpcomingReservations(id);
        List<ReservationExtendedDTO> dtos = new ArrayList<>();
        for (Reservation r : reservations) {
            ReservationExtendedDTO red = new ReservationExtendedDTO(r);
            red.setName(offerService.getNameForReservationView(r.getOffer().getId()));
            dtos.add(red);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getClientPastReservations/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ReservationExtendedDTO>> getClientsPastReservations(@PathVariable Integer id) {
        List<Reservation> reservations = reservationService.getClientsPastReservations(id);
        List<ReservationExtendedDTO> dtos = new ArrayList<>();
        for (Reservation r : reservations) {
            ReservationExtendedDTO red = new ReservationExtendedDTO(r);
            red.setName(offerService.getNameForReservationView(r.getOffer().getId()));
            dtos.add(red);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(path = "/cancelReservation/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> cancelReservation(@PathVariable Integer id) {
        reservationService.cancelReservation(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
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


    private void setAttributes(Reservation r, ReservationDTOstring dto) {
        r.setId(dto.getId());

        r.setStart(getLocalDatetimeFromVuePicker(dto.getFromDate()));
        r.setEnd(getLocalDatetimeFromVuePicker(dto.getToDate()));
        Offer offer = offerService.findOne(dto.getOfferId());
        r.setOffer(offer);
        Client client = userService.findClientByEmail(dto.getClientEmail());
        r.setClient(client);
        r.setReservationStatus(ReservationStatus.PENDING);
    }

    private LocalDateTime getLocalDatetimeFromVuePicker(String d)
    {
        String sub = d.substring(0, 24);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy kk:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(sub, formatter);
        return localDateTime;
    }
}
