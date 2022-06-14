package com.example.demo.dto;

import com.example.demo.model.Feedback;

public class ReviewDTO {
    private ClientDTO client;
    private Double rating;
    private String comment;

    public ReviewDTO() {
    }

    public ReviewDTO(Feedback feedback) {
        this.client = new ClientDTO(feedback.getReservation().getClient());
        this.rating = feedback.getRating();
        this.comment = feedback.getComment();
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
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
}
