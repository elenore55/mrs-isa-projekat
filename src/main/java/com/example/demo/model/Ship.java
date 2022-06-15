package com.example.demo.model;

import com.example.demo.model.enums.ShipType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship extends Offer {

    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    @Column(name = "shipLength")
    private Double length;

    @Column
    private Integer numberOfEngines;

    @Column
    private Integer powerOfEngine;

    @Column
    private Integer maxSpeed;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NavigationEquipment> navigationEquipmentList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    @Column
    private Integer capacity;

    @Column
    private String cancellationConditions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ship_owner_id")
    private ShipOwner owner;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FastReservation> fastReservations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
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

    public Double getRateOrNegativeOne()
    {
        double sum = 0;
        int n = 0;
        for(Reservation r : getReservations())
        {
            if (r.getFeedback()!= null)
            {
                sum += r.getFeedback().getRating();
                n++;
            }
        }
        if (n==0) return -1.0;
        return sum/n;
    }
}
