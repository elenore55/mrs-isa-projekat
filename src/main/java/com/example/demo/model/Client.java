package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends User {

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sub", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"))
    private List<Offer> subscriptions = new ArrayList<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public boolean getSubscriptionsByID(Integer offerID) {
        for(Offer o : subscriptions)
            if(o.getId()==offerID)
                return true;
        return false;
    }

    public int findSubscriptionsByOffer(Integer offerID) {
        for(Offer o : subscriptions)
            if(o.getId()==offerID) {
                o.setId(-1);
                return 1;
            }
        return -1;
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

    public void addSub(Offer o)
    {
        this.subscriptions.add(o);
    }
}
