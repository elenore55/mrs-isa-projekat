package com.example.demo.controller;

import com.example.demo.dto.ComplaintDTO;
import com.example.demo.dto.FeedbackDTO;
import com.example.demo.model.Complaint;
import com.example.demo.model.Feedback;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;
import com.example.demo.service.FeedbackService;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/feedback")
public class FeedbackController {
    private FeedbackService feedbackService;
    private ReservationService reservationService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, ReservationService reservationService)
    {
        this.feedbackService = feedbackService;
        this.reservationService = reservationService;
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> addFeedback(@RequestBody FeedbackDTO feedbackDTO){
        Feedback f = new Feedback(feedbackDTO);
        Reservation r = reservationService.findOne(feedbackDTO.getReservationId());
        Feedback savedFeedback = feedbackService.save(f);
        r.setFeedback(savedFeedback);
        reservationService.save(r);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
}
