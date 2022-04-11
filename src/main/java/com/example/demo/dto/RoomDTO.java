package com.example.demo.dto;

import java.util.List;

public class RoomDTO {
    private Integer numberOfBeds;
    private List<String> imagePaths;

    public RoomDTO() {
    }

    public RoomDTO(Integer numberOfBeds, List<String> imagePaths) {
        this.numberOfBeds = numberOfBeds;
        this.imagePaths = imagePaths;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }
}
