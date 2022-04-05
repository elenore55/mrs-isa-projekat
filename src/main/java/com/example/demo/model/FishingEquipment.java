package com.example.demo.model;

public class FishingEquipment {
    private String name;
    private Integer amount;

    public FishingEquipment() {
    }

    public FishingEquipment(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
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
