package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.DiscriminatorType.STRING;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = STRING)
@DiscriminatorValue("OTH")
public class FastReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "reservation_start")
    protected LocalDateTime start;

    @Column
    protected Integer duration;

    @Column
    protected LocalDateTime actionStart;

    @Column
    protected Integer actionDuration;

    @Column
    protected Integer maxPeople;

    @Column
    protected String additionalServices;

    @Column
    protected BigDecimal price;

    public FastReservation() {

    }

    public FastReservation(LocalDateTime start, Integer duration, Integer maxPeople, String additionalServices, BigDecimal price) {
        this.start = start;
        this.duration = duration;
        this.maxPeople = maxPeople;
        this.additionalServices = additionalServices;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getActionStart() {
        return actionStart;
    }

    public void setActionStart(LocalDateTime actionStart) {
        this.actionStart = actionStart;
    }

    public Integer getActionDuration() {
        return actionDuration;
    }

    public void setActionDuration(Integer actionDuration) {
        this.actionDuration = actionDuration;
    }

    public LocalDateTime getEnd() {
        return start.plusDays(duration);
    }
}
