package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Adventure extends Offer {

    @Column
    private Integer maxPeople;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fishing_instructor_id")
    private FishingInstructor instructor;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<FishingEquipment> fishingEquipments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FastAdventureReservation> fastAdventureReservations = new ArrayList<>();

    public Adventure() {
        super();
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public FishingInstructor getInstructor() {
        return instructor;
    }

    public void setInstructor(FishingInstructor instructor) {
        this.instructor = instructor;
    }

    public List<FishingEquipment> getFishingEquipments() {
        return fishingEquipments;
    }

    public void setFishingEquipments(List<FishingEquipment> fishingEquipments) {
        this.fishingEquipments = fishingEquipments;
    }

    public List<FastAdventureReservation> getFastAdventureReservations() {
        return fastAdventureReservations;
    }

    public void setFastAdventureReservations(List<FastAdventureReservation> fastAdventureReservations) {
        this.fastAdventureReservations = fastAdventureReservations;
    }
}
