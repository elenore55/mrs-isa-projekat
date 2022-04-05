package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FastReservation {
    protected LocalDateTime start;
    protected Integer duration;
    protected Integer maxPeople;
    protected String additionalServices;
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
}
