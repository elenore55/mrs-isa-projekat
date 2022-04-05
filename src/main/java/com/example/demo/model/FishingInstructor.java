package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class FishingInstructor extends User {
    private String biography;
    private List<Adventure> adventures = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public FishingInstructor() {
        super();
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Adventure> getAdventures() {
        return adventures;
    }

    public void setAdventures(List<Adventure> adventures) {
        this.adventures = adventures;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
