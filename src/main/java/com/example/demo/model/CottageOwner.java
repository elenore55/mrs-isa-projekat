package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class CottageOwner extends User {
    private List<Cottage> cottages = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public CottageOwner() {
        super();
    }

    public List<Cottage> getCottages() {
        return cottages;
    }

    public void setCottages(List<Cottage> cottages) {
        this.cottages = cottages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
