package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("ADV")
public class FastAdventureReservation extends FastReservation {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address place;

    public FastAdventureReservation() {
        super();
    }

    public FastAdventureReservation(LocalDateTime start, Integer duration, Integer maxPeople, String additionalServices, BigDecimal price, Address place) {
        super(start, duration, maxPeople, additionalServices, price);
        this.place = place;
    }

    public Address getPlace() {
        return place;
    }

    public void setPlace(Address place) {
        this.place = place;
    }
}
