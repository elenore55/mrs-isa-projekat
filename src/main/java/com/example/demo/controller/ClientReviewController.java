package com.example.demo.controller;

import com.example.demo.dto.ClientReviewDTO;
import com.example.demo.dto.ComplaintAdminDTO;
import com.example.demo.model.*;
import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.service.ClientReviewService;
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
@RequestMapping(value = "api/clientReviews")
public class ClientReviewController {
    private UserService userService;
    private ClientReviewService clientReviewService;
    private ReservationService reservationService;

    @Autowired
    public ClientReviewController(UserService userService, ClientReviewService clientReviewService, ReservationService reservationService) {
        this.userService = userService;
        this.clientReviewService = clientReviewService;
        this.reservationService = reservationService;
    }

    @ResponseBody
    @RequestMapping(path = "/addReview", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Void> addReview(@RequestBody ClientReviewDTO dto) {
        Client client = userService.findClientByEmail(dto.getClientEmail());
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Reservation reservation = reservationService.findOne(dto.getReservationId());
        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userService.findOne(dto.getOwnerId());
        if (!(user instanceof CottageOwner) && !(user instanceof ShipOwner) && !(user instanceof FishingInstructor)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ClientReview review = new ClientReview();
        review.setDateTime(LocalDateTime.now());
        review.setClient(client);
        review.setIssuedBy(user);
        review.setContent(dto.getContent());
        review.setPenaltyRequested(dto.getPenaltyRequested());
        review.setReservation(reservation);
        clientReviewService.save(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/allPending")
    public ResponseEntity<List<ClientReviewDTO>> getAllPendingComplaints(){
        List<ClientReview> clientReviews = clientReviewService.findAll();
        List<ClientReviewDTO> clientReviewDTOS = new ArrayList<>();
        for(ClientReview clientReview : clientReviews) {
            if(clientReview.getPenaltyRequested())
                clientReviewDTOS.add(new ClientReviewDTO(clientReview));
        }
        return new ResponseEntity<>(clientReviewDTOS, HttpStatus.OK);
    }

    // postavljamo bool da trazi penalty na false da ga ne bi prikazivali
    @ResponseBody
    @RequestMapping(path = "/updatePenaltyAdmin", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ClientReview> updateComplaintAdmin(@RequestBody ClientReview clientReview)
    {
        System.out.println(clientReview.toString());
        ClientReview izBaze = clientReviewService.findOne(clientReview.getId());
        if(clientReview.getPenaltyRequested())
            izBaze.setPenaltyRequested(false);
        izBaze = clientReviewService.update(izBaze);
        return  new ResponseEntity<>(izBaze,HttpStatus.ACCEPTED);
    }

}
