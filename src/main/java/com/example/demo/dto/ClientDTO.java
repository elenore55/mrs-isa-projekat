package com.example.demo.dto;

import com.example.demo.model.Client;

public class ClientDTO {
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private AddressDTO address;
    private String category;
    private Integer numberOfPoints;
    private Integer numberOfPenalties;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.email = client.getEmail();
        this.name = client.getName();
        this.surname = client.getSurname();
        this.phoneNumber = client.getPhoneNumber();
        this.address = new AddressDTO(client.getAddress());
        this.category = client.getCategory().toString();
        this.numberOfPoints = client.getNumberOfPoints();
        this.numberOfPoints = 0;
        if (client.getPenalties() != null)
            this.numberOfPoints = client.getPenalties().size();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public Integer getNumberOfPenalties() {
        return numberOfPenalties;
    }

    public void setNumberOfPenalties(Integer numberOfPenalties) {
        this.numberOfPenalties = numberOfPenalties;
    }
}
