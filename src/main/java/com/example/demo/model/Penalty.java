package com.example.demo.model;

import java.time.LocalDate;

public class Penalty {
    private Client client;
    private Reservation reservation;
    private LocalDate date;
    private String reason;

    public Penalty() {

    }

    public Penalty(Client client, Reservation reservation, LocalDate date, String reason) {
        this.reservation = reservation;
        this.client = client;
        this.date = date;
        this.reason = reason;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
