package com.example.demo.dto;

import com.example.demo.model.*;
import com.example.demo.model.enums.ShipType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShipDTO {
    private Integer id;
    private String name;
    private String description;
    private AddressDTO address;
    private BigDecimal price;
    private List<String> rules;
    private String additionalInfo;
    private Integer ownerId;
    private List<String> imagePaths;
    private Integer shipType;
    private Double length;
    private Integer capacity;
    private Integer numberOfEngines;
    private Integer powerOfEngine;
    private Integer maxSpeed;
    private String cancellationConditions;
    private List<FishingEquipmentDTO> fishingEquipmentList;
    private List<NavigationEquipmentDTO> navigationEquipmentList;
    private String shipTypeStr;

    public ShipDTO(Ship ship) {
        this.id = ship.getId();
        this.name = ship.getName();
        this.description = ship.getDescription();
        this.address = new AddressDTO(ship.getAddress());
        this.price = ship.getPriceList();
        this.rules = new ArrayList<>();
        for (Rule rule : ship.getRules()) this.rules.add(rule.getText());
        this.additionalInfo = ship.getAdditionalInfo();
        this.ownerId = ship.getOwner().getId();
        this.shipType = ship.getShipType().ordinal();
        this.length = ship.getLength();
        this.capacity = ship.getCapacity();
        this.numberOfEngines = ship.getNumberOfEngines();
        this.powerOfEngine = ship.getPowerOfEngine();
        this.maxSpeed = ship.getMaxSpeed();
        this.cancellationConditions = ship.getCancellationConditions();
        this.fishingEquipmentList = new ArrayList<>();
        for (FishingEquipment fe : ship.getFishingEquipmentList())
            fishingEquipmentList.add(new FishingEquipmentDTO(fe));
        this.navigationEquipmentList = new ArrayList<>();
        for (NavigationEquipment ne : ship.getNavigationEquipmentList())
            navigationEquipmentList.add(new NavigationEquipmentDTO(ne));
        this.imagePaths = new ArrayList<>();
        for (Image img : ship.getImages()) {
            imagePaths.add(img.getPath());
        }
    }

    public ShipDTO() {}

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

    public Integer getShipType() {
        return shipType;
    }

    public void setShipType(Integer shipType) {
        this.shipType = shipType;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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

    public String getCancellationConditions() {
        return cancellationConditions;
    }

    public void setCancellationConditions(String cancellationConditions) {
        this.cancellationConditions = cancellationConditions;
    }

    public List<FishingEquipmentDTO> getFishingEquipmentList() {
        return fishingEquipmentList;
    }

    public void setFishingEquipmentList(List<FishingEquipmentDTO> fishingEquipmentList) {
        this.fishingEquipmentList = fishingEquipmentList;
    }

    public List<NavigationEquipmentDTO> getNavigationEquipmentList() {
        return navigationEquipmentList;
    }

    public void setNavigationEquipmentList(List<NavigationEquipmentDTO> navigationEquipmentList) {
        this.navigationEquipmentList = navigationEquipmentList;
    }

    public String getShipTypeStr() {
        return ShipType.values()[shipType].toString();
    }
}
