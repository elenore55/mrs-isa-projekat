package com.example.demo.model;

import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.model.enums.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "offer_id")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    protected String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    protected Address address;

    @Column
    protected String description;


    @OneToMany(cascade = CascadeType.ALL)        //izmenjeno
    protected List<PriceList> priceHistory;

    @Column
    protected BigDecimal priceList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Rule> rules = new ArrayList<>();

    @Column
    protected String additionalInfo;

    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Availability> availabilities;

    @ManyToMany(mappedBy = "subscriptions", fetch = FetchType.LAZY)
    protected List<Client> subscribers = new ArrayList<>();

    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Reservation> reservations = new ArrayList<>();

    public Offer() {
    }

    public Boolean hasFutureReservations() {
        for (Reservation r : reservations) {
            if (r.getEnd().compareTo(LocalDateTime.now()) >= 0 && r.getReservationStatus() != ReservationStatus.CANCELLED) {
                return true;
            }
        }
        return false;
    }

    public List<Feedback> getReviews() {
        List<Feedback> reviews = new ArrayList<>();
        for (Reservation r : reservations) {
            Feedback fb = r.getFeedback();
            if (fb != null && fb.getStatus() == AdminApprovalStatus.APPROVED) reviews.add(r.getFeedback());
        }
        return reviews;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPriceList() {
        return priceList;
    }

    public void setPriceList(BigDecimal priceList) {
        this.priceList = priceList;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public List<Client> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Client> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<PriceList> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(List<PriceList> priceHistory) {
        this.priceHistory = priceHistory;
    }
}
