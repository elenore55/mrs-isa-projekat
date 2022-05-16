package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cottage extends Offer {

    @OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cottage_owner_id")
    private CottageOwner owner;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FastReservation> fastReservations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public double getRateOrNegativeOne()
    {
        double sum = 0;
        int n = 0;
        for(Reservation r : getReservations())
        {
            if (r.getFeedback()!= null)
            {
                sum += r.getFeedback().getRating();
                n++;
            }
        }
        if (n==0) return -1;
        return sum/n;
    }
}
