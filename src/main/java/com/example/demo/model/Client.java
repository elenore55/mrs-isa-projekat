package com.example.demo.model;

import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.enums.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends User {

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "sub", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"))
    private List<Offer> subscriptions = new ArrayList<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Penalty> penalties = new ArrayList<>();

    public Client() {
        super();
    }

    public Client(RegistrationDTO r)
    {
        this.profileData = new ProfileData(r.getEmail(), r.getPassword(), r.getName(), r.getSurname(), r.getTel(),
                new Address(r.getStreet(), r.getCity(), r.getCountry()));
        this.numberOfPoints = 0;
        this.category = Category.REGULAR;

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
