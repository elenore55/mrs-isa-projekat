package com.example.demo.model;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.model.enums.AdminApprovalStatus;

import javax.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double rating;

    @Column
    private String comment;

    @Enumerated(EnumType.STRING)
    private AdminApprovalStatus status;

    @OneToOne(mappedBy = "feedback")
    private Reservation reservation;


    public Feedback() {
    }

    public Feedback(FeedbackDTO feedbackDTO) {
        this.rating = feedbackDTO.getRating();
        this.comment = feedbackDTO.getComment();
        this.status = AdminApprovalStatus.PENDING;
        // nisam postavila rezervaciju
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AdminApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(AdminApprovalStatus status) {
        this.status = status;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
