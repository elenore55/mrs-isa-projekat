package com.example.demo.dto;

import com.example.demo.model.Reservation;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Integer id;
    private String clientEmail;
    private Integer offerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ReservationDTO() {
    }

    public ReservationDTO(Reservation r) {
        this.id = r.getId();
        if (r.getClient() != null)
            this.clientEmail = r.getClient().getEmail();
        this.offerId = r.getOffer().getId();
        this.startDate = r.getStart();
        this.endDate =  r.getEnd();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}