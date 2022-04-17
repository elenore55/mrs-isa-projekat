package com.example.demo.dto;

import com.example.demo.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AdventureDTO {
    private Integer id;
    private String name;
    private Address address;
    private String description;
    private BigDecimal price;
    private String additionalInfo;
    private Integer fInstructorId;
    private List<String> imagePaths;
    private List<String> rules;
    private List<FishingEquipmentDTO> fishingEquipmentList;
    private Integer maxPeople;

    public AdventureDTO()
    {

    }
    public AdventureDTO(Integer id, String name, Address address, String description, BigDecimal price, String additionalInfo, Integer fInstructorId, List<String> imagePaths, List<String> rules, List<FishingEquipmentDTO> fishingEquipmentList, Integer maxPeople) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.price = price;
        this.additionalInfo = additionalInfo;
        this.fInstructorId = fInstructorId;
        this.imagePaths = imagePaths;
        this.rules = rules;
        this.fishingEquipmentList = fishingEquipmentList;
        this.maxPeople = maxPeople;
    }

    public AdventureDTO(Adventure adventure)
    {
        this.id = adventure.getId();
        this.name = adventure.getName();
        this.address = adventure.getAddress();
        this.description = adventure.getDescription();
        this.price = adventure.getPriceList().getAmount();
        this.additionalInfo = adventure.getAdditionalInfo();
        this.fInstructorId = adventure.getInstructor().getId();

        this.imagePaths = new ArrayList<>();
        for(Image image : adventure.getImages())
            imagePaths.add(image.getPath());

        this.rules = new ArrayList<>();
        for(Rule rule : adventure.getRules())
            rules.add(rule.getText());

        this.fishingEquipmentList = new ArrayList<>();
        for(FishingEquipment fishingEquipment : adventure.getFishingEquipments())
            fishingEquipmentList.add(new FishingEquipmentDTO(fishingEquipment));
        this.maxPeople = adventure.getMaxPeople();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Integer getfInstructorId() {
        return fInstructorId;
    }

    public void setfInstructorId(Integer fInstructorId) {
        this.fInstructorId = fInstructorId;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public List<FishingEquipmentDTO> getFishingEquipmentList() {
        return fishingEquipmentList;
    }

    public void setFishingEquipmentList(List<FishingEquipmentDTO> fishingEquipmentList) {
        this.fishingEquipmentList = fishingEquipmentList;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }
}
