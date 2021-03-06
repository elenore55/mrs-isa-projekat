package com.example.demo.controller;

import com.example.demo.dto.ComplaintAdminDTO;
import com.example.demo.dto.ComplaintDTO;
import com.example.demo.dto.FeedbackAdminDTO;
import com.example.demo.dto.FeedbackDTO;
import com.example.demo.model.*;
import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.service.FeedbackService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/feedback")
public class FeedbackController {
    private FeedbackService feedbackService;
    private ReservationService reservationService;
    private EmailSender emailSender;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, ReservationService reservationService)
    {
        this.feedbackService = feedbackService;
        this.reservationService = reservationService;
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> addFeedback(@RequestBody FeedbackDTO feedbackDTO){
        Feedback f = new Feedback(feedbackDTO);
        Reservation r = reservationService.findOne(feedbackDTO.getReservationId());
        Feedback savedFeedback = feedbackService.save(f);
        r.setFeedback(savedFeedback);
        reservationService.save(r);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @GetMapping(path = "/allPending")
    public ResponseEntity<List<FeedbackAdminDTO>> getAllPendingFeedbacks(){
        List<Feedback> feedbacks = feedbackService.findAll();
        List<FeedbackAdminDTO> feedbackDTOS = new ArrayList<>();
        for(Feedback feedback : feedbacks) {
            if(feedback.getStatus() == AdminApprovalStatus.PENDING)
                feedbackDTOS.add(new FeedbackAdminDTO(feedback));
        }
        return new ResponseEntity<>(feedbackDTOS, HttpStatus.OK);
    }

    @GetMapping(path = "/approvalStatus")
    public ResponseEntity<List<AdminApprovalStatus>> getFeedbackPossibleStatuses(){
        List<AdminApprovalStatus> statuses = new ArrayList<>();
        statuses.add(AdminApprovalStatus.APPROVED);
        statuses.add(AdminApprovalStatus.REJECTED);
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(path = "/updateFeedbackAdmin", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Feedback> updateFeedbackAdmin(@RequestBody FeedbackAdminDTO feedbackAdminDTO)
    {
        System.out.println(feedbackAdminDTO.toString());
        Feedback izBaze = feedbackService.findOne(feedbackAdminDTO.getId());
        if(feedbackAdminDTO.getStatus()==AdminApprovalStatus.APPROVED || feedbackAdminDTO.getStatus()==AdminApprovalStatus.REJECTED)
            izBaze.setStatus(feedbackAdminDTO.getStatus());
        izBaze = feedbackService.update(izBaze);

        Reservation reservation = reservationService.findOne(feedbackAdminDTO.getReservationId());

        emailSender.send(reservation.getClient().getEmail(), "Feedback", feedbackAdminDTO.getStatus().toString());

//        Offer offer = reservation.getOffer();
//        emailSender.send(offer.getOwnerID(), "Adventure", "You made an adventure reservation");

        return  new ResponseEntity<>(izBaze,HttpStatus.ACCEPTED);
    }
}
