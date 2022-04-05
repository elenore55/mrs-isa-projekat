package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Adventure extends Offer {
    private Integer maxPeople;
    private List<String> images = new ArrayList<>();
    private FishingInstructor instructor;
    private List<FishingEquipment> fishingEquipments = new ArrayList<>();
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
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
