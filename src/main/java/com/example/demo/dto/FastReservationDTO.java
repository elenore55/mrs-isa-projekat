package com.example.demo.dto;

import com.example.demo.model.FastReservation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<String> additionalServices;

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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        this.startStr = dateTimeFormatter.format(start);
        this.actionStartStr = dateTimeFormatter.format(actionStart);
        this.additionalServices = new ArrayList<>();
        if (fr.getAdditionalServices() != null) {
            this.additionalServices = Arrays.asList(fr.getAdditionalServices().split(","));
        }
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
        return startStr;
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }

    public String getActionStartStr() {
        return actionStartStr;
    }

    public void setActionStartStr(String actionStartStr) {
        this.actionStartStr = actionStartStr;
    }

    public List<String> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<String> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
