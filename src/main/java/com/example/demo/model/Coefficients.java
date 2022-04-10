package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Coefficients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer requiredPointsSilver;

    @Column
    private Integer requiredPointsGold;

    @Column
    private Integer userReservationPoints;

    @Column
    private Integer ownerReservationPoints;

    @Column
    private Double percentageClientSilver;

    @Column
    private Double percentageClientGold;

    @Column
    private Double percentageOwnerSilver;

    @Column
    private Double percentageOwnerGold;

    @Column
    private Double reservationPercentage;

    public Coefficients() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequiredPointsSilver() {
        return requiredPointsSilver;
    }

    public void setRequiredPointsSilver(Integer requiredPointsSilver) {
        this.requiredPointsSilver = requiredPointsSilver;
    }

    public Integer getRequiredPointsGold() {
        return requiredPointsGold;
    }

    public void setRequiredPointsGold(Integer requiredPointsGold) {
        this.requiredPointsGold = requiredPointsGold;
    }

    public Integer getUserReservationPoints() {
        return userReservationPoints;
    }

    public void setUserReservationPoints(Integer userReservationPoints) {
        this.userReservationPoints = userReservationPoints;
    }

    public Integer getOwnerReservationPoints() {
        return ownerReservationPoints;
    }

    public void setOwnerReservationPoints(Integer ownerReservationPoints) {
        this.ownerReservationPoints = ownerReservationPoints;
    }

    public Double getPercentageClientSilver() {
        return percentageClientSilver;
    }

    public void setPercentageClientSilver(Double percentageClientSilver) {
        this.percentageClientSilver = percentageClientSilver;
    }

    public Double getPercentageClientGold() {
        return percentageClientGold;
    }

    public void setPercentageClientGold(Double percentageClientGold) {
        this.percentageClientGold = percentageClientGold;
    }

    public Double getPercentageOwnerSilver() {
        return percentageOwnerSilver;
    }

    public void setPercentageOwnerSilver(Double percentageOwnerSilver) {
        this.percentageOwnerSilver = percentageOwnerSilver;
    }

    public Double getPercentageOwnerGold() {
        return percentageOwnerGold;
    }

    public void setPercentageOwnerGold(Double percentageOwnerGold) {
        this.percentageOwnerGold = percentageOwnerGold;
    }

    public Double getReservationPercentage() {
        return reservationPercentage;
    }

    public void setReservationPercentage(Double reservationPercentage) {
        this.reservationPercentage = reservationPercentage;
    }
}

