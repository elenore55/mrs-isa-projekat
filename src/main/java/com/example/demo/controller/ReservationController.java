package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.model.FishingInstructor;
import com.example.demo.service.FishingInstructorService;
import com.example.demo.service.OfferService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    @PreAuthorize("hasAnyRole('COTTAGE', 'SHIP')")
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
    @RequestMapping(path = "/getClientsSubs/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<SubDTO>> getClientsSubs(@PathVariable Integer id) {
        Client c = (Client) userService.findById(id);

        List<Offer> subs = c.getSubscriptions();
        List<SubDTO> dtos = new ArrayList<>();
        for (Offer o : subs) {
            SubDTO s = new SubDTO(o);
            offerService.setImage(o, s);
            dtos.add(s);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getOfferActions/{offerId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<FastReservationDTO>> getOfferActions(@PathVariable Integer offerId) {
        List<FastReservation> fast = getFastReservations(offerId);
        List<FastReservationDTO> dtos = new ArrayList<>();
        for (FastReservation f : fast) {
            FastReservationDTO dto = new FastReservationDTO(f);
            dtos.add(dto);
        }
        return new ResponseEntity<List<FastReservationDTO>>(dtos, HttpStatus.OK);
    }


    private List<FastReservation> getFastReservations(Integer offerId) {
        List<FastReservation> retVal = new ArrayList<>();
        Offer o = offerService.findOne(offerId);
        if (o instanceof Cottage)
        {
            retVal = ((Cottage) o).getFastReservations();
        }
        else if (o instanceof Ship)
        {
            retVal = ((Ship) o).getFastReservations();
        }
        return retVal;
    }

    @ResponseBody
    @RequestMapping(path = "/unfollow/{clientId}/{offerId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> unfollow(@PathVariable Integer clientId, @PathVariable Integer offerId) {
        Client c = (Client) userService.findById(clientId);
        List<Offer> subs = c.getSubscriptions();
        for (Offer o : c.getSubscriptions())
        {
            if (o.getId().equals(offerId))
            {
                subs.remove(o);
                break;
            }
        }
        userService.unfollow(c);
        return new ResponseEntity<>("OK", HttpStatus.OK);
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
    @RequestMapping(path = "/getClientPastReservations/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<ReservationExtendedDTO>> getClientsPastReservations(@RequestBody FilterPastDTO filterPastDTO) {
        System.err.println("SKRETANJE PAZNJE");
        List<Reservation> reservations = reservationService.getClientsPastReservations(filterPastDTO);
        reservationService.sortPastReservations(reservations, filterPastDTO.getSortBy());
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
