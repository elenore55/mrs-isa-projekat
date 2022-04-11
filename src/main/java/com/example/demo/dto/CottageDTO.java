package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public class CottageDTO {
    private String name;
    private AddressDTO address;
    private List<RoomDTO> rooms;
    private BigDecimal price;
    private List<String> rules;
    private String additionalInfo;
    private Integer ownerId;

    public CottageDTO() {
    }

    public CottageDTO(String name, AddressDTO address, List<RoomDTO> rooms, BigDecimal price, List<String> rules, String additionalInfo, Integer ownerId) {
        this.name = name;
        this.address = address;
        this.rooms = rooms;
        this.price = price;
        this.rules = rules;
        this.additionalInfo = additionalInfo;
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
