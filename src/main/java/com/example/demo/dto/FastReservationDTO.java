package com.example.demo.dto;

import com.example.demo.model.FastReservation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FastReservationDTO {
    private Integer id;
    private LocalDateTime start;
    private Integer duration;
    private LocalDateTime actionStart;
    private Integer actionDuration;
    private BigDecimal price;
    private Integer maxPeople;
    private String startStr;
    private String actionStartStr;

    public FastReservationDTO() {
    }

    public FastReservationDTO(FastReservation fr) {
        this.id = fr.getId();
        this.start = fr.getStart();
        this.duration = fr.getDuration();
        this.actionStart = fr.getActionStart();
        this.actionDuration = fr.getActionDuration();
        this.price = fr.getPrice();
        this.maxPeople = fr.getMaxPeople();
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getStartStr() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        return dateTimeFormatter.format(start);
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }

    public String getActionStartStr() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        return dateTimeFormatter.format(actionStart);
    }

    public void setActionStartStr(String actionStartStr) {
        this.actionStartStr = actionStartStr;
    }
}
