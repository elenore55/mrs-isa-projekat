package com.example.demo.model;

import com.example.demo.model.enums.ReservationStatus;

import java.time.LocalDateTime;

public class Reservation {
    private LocalDateTime start;
    private LocalDateTime end;
    private ReservationStatus reservationStatus;
    private Client client;
    private Feedback feedback;
    private Offer offer;

    public Reservation() {
    }

    public Reservation(LocalDateTime start, LocalDateTime end, Client client, Offer offer) {
        this.start = start;
        this.end = end;
        this.reservationStatus = ReservationStatus.PENDING;
        this.client = client;
        this.offer = offer;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
