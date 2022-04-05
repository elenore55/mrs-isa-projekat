package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private List<Reservation> reservations = new ArrayList<>();
    private List<Offer> subscriptions = new ArrayList<>();
    private List<Penalty> penalties = new ArrayList<>();

    public Client() {
        super();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Offer> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Offer> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Penalty> getPenalties() {
        return penalties;
    }

    public void setPenalties(List<Penalty> penalties) {
        this.penalties = penalties;
    }
}
