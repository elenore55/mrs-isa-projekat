package com.example.demo.dto;

import com.example.demo.model.Cottage;
import com.example.demo.model.Image;
import com.example.demo.model.Room;
import com.example.demo.model.Rule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CottageDTO {
    private Integer id;
    private String name;
    private String description;
    private AddressDTO address;
    private List<RoomDTO> rooms;
    private BigDecimal price;
    private List<String> rules;
    private String additionalInfo;
    private Integer ownerId;
    private List<String> imagePaths;

    public CottageDTO() {
    }

    public CottageDTO(Integer id, String name, String description, AddressDTO address, List<RoomDTO> rooms, BigDecimal price, List<String> rules, String additionalInfo, Integer ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.rooms = rooms;
        this.price = price;
        this.rules = rules;
        this.additionalInfo = additionalInfo;
        this.ownerId = ownerId;
    }

    public CottageDTO(Cottage cottage) {
        this.id = cottage.getId();
        this.name = cottage.getName();
        this.description = cottage.getDescription();
        this.address = new AddressDTO(cottage.getAddress());
        this.rooms = new ArrayList<>();
        for (Room room : cottage.getRooms()) {
            rooms.add(new RoomDTO(room));
        }
        this.price = cottage.getPriceList().getAmount();
        this.rules = new ArrayList<>();
        for (Rule rule : cottage.getRules()) {
            rules.add(rule.getText());
        }
        this.additionalInfo = cottage.getAdditionalInfo();
        this.ownerId = cottage.getOwner().getId();
        this.imagePaths = new ArrayList<>();
        for (Image img : cottage.getImages()) {
            imagePaths.add(img.getPath());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }
}
