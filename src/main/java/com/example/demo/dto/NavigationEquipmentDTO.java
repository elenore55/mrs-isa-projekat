package com.example.demo.dto;

import com.example.demo.model.NavigationEquipment;

public class NavigationEquipmentDTO {
    private Integer id;
    private String name;
    private Integer amount;

    public NavigationEquipmentDTO() {}

    public NavigationEquipmentDTO(Integer id, String name, Integer amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public NavigationEquipmentDTO(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public NavigationEquipmentDTO(NavigationEquipment equipment) {
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.amount = equipment.getAmount();
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
