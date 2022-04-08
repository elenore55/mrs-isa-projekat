package com.example.demo.model;

public class NavigationEquipment {
    private String name;
    private Integer amount;

    public NavigationEquipment() {
    }

    public NavigationEquipment(String name, Integer amount) {
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
