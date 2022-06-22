package com.example.demo.dto;

import com.example.demo.model.Reservation;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Integer id;
    private String clientEmail;
    private ClientDTO client;
    private Integer offerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer ownerId;
    private String status;
    private String offerName;
    private String reviewOfClient;

    public ReservationDTO() {
    }

    public ReservationDTO(Reservation r) {
        this.id = r.getId();
        if (r.getClient() != null) {
            this.clientEmail = r.getClient().getEmail();
            this.client = new ClientDTO(r.getClient());
        }
        this.offerId = r.getOffer().getId();
        this.startDate = r.getStart();
        this.endDate = r.getEnd();
        this.status = r.getReservationStatus().toString();
        this.offerName = r.getOffer().getName();
        if (r.getClientReview() != null) this.reviewOfClient = r.getClientReview().getContent();
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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public String getReviewOfClient() {
        return reviewOfClient;
    }

    public void setReviewOfClient(String reviewOfClient) {
        this.reviewOfClient = reviewOfClient;
    }
}
