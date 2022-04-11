package com.example.demo.dto;

import com.example.demo.model.Image;
import com.example.demo.model.Room;

import java.util.ArrayList;
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

    public RoomDTO(Room room) {
        this.numberOfBeds = room.getNumberOfBeds();
        this.imagePaths = new ArrayList<>();
        for (Image img : room.getImages()) {
            imagePaths.add(img.getPath());
        }
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
