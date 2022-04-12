package com.example.demo.dto;

import com.example.demo.model.Room;

public class RoomDTO {
    private Integer id;
    private Integer numberOfBeds;

    public RoomDTO() {
    }

    public RoomDTO(Integer id, Integer numberOfBeds) {
        this.id = id;
        this.numberOfBeds = numberOfBeds;
    }

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.numberOfBeds = room.getNumberOfBeds();
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
}
