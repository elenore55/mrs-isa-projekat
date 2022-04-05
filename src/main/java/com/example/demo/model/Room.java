package com.example.demo.model;

import java.util.List;

public class Room {
    private Integer numberOfBeds;
    private List<String> images;

    public Room() {
    }

    public Room(Integer numberOfBeds, List<String> images) {
        this.numberOfBeds = numberOfBeds;
        this.images = images;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
