package com.example.demo.dto;

import com.example.demo.model.FishingEquipment;

public class FishingEquipmentDTO {
    private Integer id;
    private String name;
    private Integer amount;

    public FishingEquipmentDTO()
    {

    }

    public FishingEquipmentDTO(Integer id, String name, Integer amount)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public FishingEquipmentDTO(FishingEquipment fishingEquipment)
    {
        this.id = fishingEquipment.getId();
        this.name = fishingEquipment.getName();
        this.amount = fishingEquipment.getAmount();
    }

    public Integer getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
