package com.example.demo.dto;

import com.example.demo.model.ClientReview;

import java.time.LocalDateTime;

public class ClientReviewDTO {
    private Integer id;
    private LocalDateTime dateTime;
    private String content;
    private String clientEmail;
    private Integer ownerId;
    private Boolean penaltyRequested;
    private Integer reservationId;

    public ClientReviewDTO() {
    }

    public ClientReviewDTO(ClientReview cr) {
        this.id = cr.getId();
        this.dateTime = cr.getDateTime();
        this.content = cr.getContent();
        this.clientEmail = cr.getClient().getEmail();
        this.ownerId = cr.getIssuedBy().getId();
        this.penaltyRequested = cr.getPenaltyRequested();
        this.reservationId = cr.getReservation().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getPenaltyRequested() {
        return penaltyRequested;
    }

    public void setPenaltyRequested(Boolean penaltyRequested) {
        this.penaltyRequested = penaltyRequested;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }
}
