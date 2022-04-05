package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Cottage extends Offer {
    private List<Room> rooms = new ArrayList<>();
    private CottageOwner owner;
    private List<FastReservation> fastReservations = new ArrayList<>();

    public Cottage() {
        super();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public CottageOwner getOwner() {
        return owner;
    }

    public void setOwner(CottageOwner owner) {
        this.owner = owner;
    }

    public List<FastReservation> getFastReservations() {
        return fastReservations;
    }

    public void setFastReservations(List<FastReservation> fastReservations) {
        this.fastReservations = fastReservations;
    }
}
