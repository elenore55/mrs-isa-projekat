package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ShipOwner extends User {
    private List<Ship> ships = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public ShipOwner() {
        super();
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
