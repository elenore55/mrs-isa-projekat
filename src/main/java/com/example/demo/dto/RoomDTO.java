package com.example.demo.dto;

import com.example.demo.model.Image;
import com.example.demo.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomDTO {
    private Integer id;
    private Integer numberOfBeds;
    private List<String> imagePaths;

    public RoomDTO() {
    }

    public RoomDTO(Integer id, Integer numberOfBeds, List<String> imagePaths) {
        this.id = id;
        this.numberOfBeds = numberOfBeds;
        this.imagePaths = imagePaths;
    }

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.numberOfBeds = room.getNumberOfBeds();
        this.imagePaths = new ArrayList<>();
        for (Image img : room.getImages()) {
            imagePaths.add(img.getPath());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
