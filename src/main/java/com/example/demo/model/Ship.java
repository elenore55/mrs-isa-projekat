package com.example.demo.model;

import com.example.demo.model.enums.ShipType;

import java.util.ArrayList;
import java.util.List;

public class Ship extends Offer {
    private ShipType shipType;
    private Double length;
    private Integer numberOfEngines;
    private Integer powerOfEngine;
    private Integer maxSpeed;
    private List<NavigationEquipment> navigationEquipmentList;
    private List<String> images;
    private Integer capacity;
    private String cancellationConditions;
    private ShipOwner owner;
    private List<FastReservation> fastReservations = new ArrayList<>();
    private List<FishingEquipment> fishingEquipmentList = new ArrayList<>();

    public Ship() {
        super();
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getNumberOfEngines() {
        return numberOfEngines;
    }

    public void setNumberOfEngines(Integer numberOfEngines) {
        this.numberOfEngines = numberOfEngines;
    }

    public Integer getPowerOfEngine() {
        return powerOfEngine;
    }

    public void setPowerOfEngine(Integer powerOfEngine) {
        this.powerOfEngine = powerOfEngine;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public List<NavigationEquipment> getNavigationEquipmentList() {
        return navigationEquipmentList;
    }

    public void setNavigationEquipmentList(List<NavigationEquipment> navigationEquipmentList) {
        this.navigationEquipmentList = navigationEquipmentList;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCancellationConditions() {
        return cancellationConditions;
    }

    public void setCancellationConditions(String cancellationConditions) {
        this.cancellationConditions = cancellationConditions;
    }

    public ShipOwner getOwner() {
        return owner;
    }

    public void setOwner(ShipOwner owner) {
        this.owner = owner;
    }

    public List<FastReservation> getFastReservations() {
        return fastReservations;
    }

    public void setFastReservations(List<FastReservation> fastReservations) {
        this.fastReservations = fastReservations;
    }

    public List<FishingEquipment> getFishingEquipmentList() {
        return fishingEquipmentList;
    }

    public void setFishingEquipmentList(List<FishingEquipment> fishingEquipmentList) {
        this.fishingEquipmentList = fishingEquipmentList;
    }
}
