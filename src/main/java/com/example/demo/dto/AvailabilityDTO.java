package com.example.demo.dto;

import com.example.demo.model.Availability;
import com.example.demo.model.Offer;

import java.time.LocalDateTime;

public class AvailabilityDTO {
    private Integer id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Offer offer;

    public AvailabilityDTO() {

    }

    public AvailabilityDTO(Integer id, LocalDateTime start, LocalDateTime end, Offer offer) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.offer = offer;
    }

    public AvailabilityDTO(Availability availability)
    {
        this.id = availability.getId();
        this.start = availability.getStart();
        this.end = availability.getEnd();
        this.offer = availability.getOffer();
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

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
