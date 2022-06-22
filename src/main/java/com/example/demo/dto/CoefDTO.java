package com.example.demo.dto;

import com.example.demo.model.Coefficients;

import javax.persistence.Column;

public class CoefDTO {

    private Integer requiredPointsSilver;
    private Integer requiredPointsGold;
    private Integer userReservationPoints;
    private Integer ownerReservationPoints;
    private Double percentageClientSilver;
    private Double percentageClientGold;
    private Double percentageOwnerSilver;
    private Double percentageOwnerGold;
    private Double reservationPercentage;

    public CoefDTO(){}

    public CoefDTO(Integer requiredPointsSilver, Integer requiredPointsGold, Integer userReservationPoints, Integer ownerReservationPoints, Double percentageClientSilver, Double percentageClientGold, Double percentageOwnerSilver, Double percentageOwnerGold, Double reservationPercentage) {
        this.requiredPointsSilver = requiredPointsSilver;
        this.requiredPointsGold = requiredPointsGold;
        this.userReservationPoints = userReservationPoints;
        this.ownerReservationPoints = ownerReservationPoints;
        this.percentageClientSilver = percentageClientSilver;
        this.percentageClientGold = percentageClientGold;
        this.percentageOwnerSilver = percentageOwnerSilver;
        this.percentageOwnerGold = percentageOwnerGold;
        this.reservationPercentage = reservationPercentage;
    }
    public CoefDTO(Coefficients coefficients) {
        this.requiredPointsSilver = coefficients.getRequiredPointsSilver();
        this.requiredPointsGold = coefficients.getRequiredPointsGold();
        this.userReservationPoints = coefficients.getUserReservationPoints();
        this.ownerReservationPoints = coefficients.getOwnerReservationPoints();
        this.percentageClientSilver = coefficients.getPercentageClientSilver();
        this.percentageClientGold = coefficients.getPercentageClientGold();
        this.percentageOwnerSilver = coefficients.getPercentageOwnerSilver();
        this.percentageOwnerGold = coefficients.getPercentageOwnerGold();
        this.reservationPercentage = coefficients.getReservationPercentage();
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
