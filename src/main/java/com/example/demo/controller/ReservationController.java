package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.model.enums.AdminApprovalStatus;
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

import java.math.BigDecimal;
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

    @Transactional
    @ResponseBody
    @RequestMapping(path = "/addReservation", method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasAnyRole('COTTAGE', 'SHIP', 'CLIENT', 'ADVENTURE')")
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
    @PreAuthorize("hasRole('CLIENT')")
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
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<FastReservationDTO>> getOfferActions(@PathVariable Integer offerId) {
        List<FastReservation> fast = getFastReservations(offerId);
        List<FastReservationDTO> dtos = new ArrayList<>();
        for (FastReservation f : fast) {
            FastReservationDTO dto = new FastReservationDTO(f);
            dtos.add(dto);
        }
        return new ResponseEntity<List<FastReservationDTO>>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/getOldPrice/{offerId}", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<BigDecimal> getOldPrice(@PathVariable Integer offerId) {
        Offer o = offerService.findOne(offerId);
        BigDecimal price = o.getPriceList();
        return new ResponseEntity<BigDecimal>(price, HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(path = "/addNewFastReservation/{offerId}/{clientId}", method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasRole('CLIENT')")
    public String addFastReservation(@RequestBody FastReservationDTO fastReservationDTO, @PathVariable Integer offerId, @PathVariable Integer clientId) {
        Reservation r = new Reservation();
        r.setStart(fastReservationDTO.getStart());
        r.setEnd(fastReservationDTO.getStart().plusDays(fastReservationDTO.getDuration().longValue()));
        Offer o = offerService.findOne(offerId);
        r.setOffer(o);
        User u = userService.findById(clientId);
        r.setClient((Client) u);
        reservationService.save(r);
        return "OK";
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
    @PreAuthorize("hasRole('CLIENT')")
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
    @PreAuthorize("hasRole('CLIENT')")
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
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<ReservationExtendedDTO>> getClientsPastReservations(@RequestBody FilterPastDTO filterPastDTO) {
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
    @PreAuthorize("hasRole('CLIENT')")
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
        offer.incNumberOfReservations();
        offerService.save(offer);
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

    @GetMapping(value = "/advreser/{advId}/{instId}")
    public ResponseEntity<List<ReservationDTO>> getAdventuresReservations(@PathVariable Integer advId,@PathVariable Integer instId) {
        FishingInstructor fishingInstructor = fishingInstructorService.findOne(instId);
        List<Reservation> reservations = fishingInstructor.getReservations();


        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if(reservation.getOffer().getId().equals(advId))
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

    @GetMapping(path = "/allPendingForInstructor/{id}/{idadv}")
    public ResponseEntity<List<ReservationInstDTO>> getAllPendingReservations(@PathVariable Integer id, @PathVariable Integer idadv){
        FishingInstructor fishingInstructor = fishingInstructorService.findOne(id);
        List<Reservation> reservations = fishingInstructor.getReservations();
        List<ReservationInstDTO> reservationInstDTOS = new ArrayList<>();
        for(Reservation reservation : reservations) {
            if(reservation.getOffer().getId().equals(idadv))
                if(reservation.getReservationStatus() == ReservationStatus.PENDING || reservation.getReservationStatus() == ReservationStatus.ACTIVE || reservation.getReservationStatus() == ReservationStatus.FINISHED)
                    reservationInstDTOS.add(new ReservationInstDTO(reservation));
        }
        return new ResponseEntity<>(reservationInstDTOS, HttpStatus.OK);
    }

    @GetMapping(path = "/allForInstructor/{id}/{idadv}")
    public ResponseEntity<List<ReservationInstDTO>> getAllReservations(@PathVariable Integer id, @PathVariable Integer idadv){
        FishingInstructor fishingInstructor = fishingInstructorService.findOne(id);
        List<Reservation> reservations = fishingInstructor.getReservations();
        List<ReservationInstDTO> reservationInstDTOS = new ArrayList<>();
        for(Reservation reservation : reservations) {
            if(reservation.getOffer().getId().equals(idadv))
                reservationInstDTOS.add(new ReservationInstDTO(reservation));
        }
        return new ResponseEntity<>(reservationInstDTOS, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/updateAdventuresreservation", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Reservation> updateComplaintAdmin(@RequestBody ReservationInstDTO reservationInstDTO)
    {
        System.out.println(reservationInstDTO.toString());
        Reservation izBaze = reservationService.findOne(reservationInstDTO.getId());
        if(reservationInstDTO.getStatus()==ReservationStatus.CANCELLED || reservationInstDTO.getStatus()==ReservationStatus.CLIENT_NOT_ARRIVED || reservationInstDTO.getStatus()==ReservationStatus.FINISHED )
            izBaze.setReservationStatus(reservationInstDTO.getStatus());
        izBaze = reservationService.save(izBaze);
        return  new ResponseEntity<>(izBaze,HttpStatus.ACCEPTED);
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
