package com.example.demo.dto;

import com.example.demo.model.Feedback;
import com.example.demo.model.enums.AdminApprovalStatus;

public class FeedbackAdminDTO {
    private String comment;
    private double rating;
    private int reservationId;
    private int id;
    private AdminApprovalStatus status;

    public FeedbackAdminDTO() {
    }

    public FeedbackAdminDTO(String comment, double rating, int reservationId, int id) {
        this.comment = comment;
        this.rating = rating;
        this.reservationId = reservationId;
        this.id = id;
    }
    public FeedbackAdminDTO(Feedback feedback) {
        this.comment = feedback.getComment();
        this.rating = feedback.getRating();
        this.reservationId = feedback.getReservation().getId();
        this.status = feedback.getStatus();
        this.id = feedback.getId();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AdminApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(AdminApprovalStatus status) {
        this.status = status;
    }
}
