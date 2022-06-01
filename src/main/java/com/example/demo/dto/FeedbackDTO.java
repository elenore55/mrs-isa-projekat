package com.example.demo.dto;

import com.example.demo.model.enums.AdminApprovalStatus;

public class FeedbackDTO {
    private String comment;
    private double rating;
    private int reservationId;

    public FeedbackDTO() { }
    public FeedbackDTO(String comment, double rating, int reservationId) {
        this.comment = comment;
        this.rating = rating;
        this.reservationId = reservationId;
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
}
