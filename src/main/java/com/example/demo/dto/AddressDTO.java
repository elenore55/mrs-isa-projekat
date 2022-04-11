package com.example.demo.dto;

import com.example.demo.model.Address;

public class AddressDTO {
    private String street;
    private String city;
    private String country;

    public AddressDTO() {
    }

    public AddressDTO(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.city = address.getCity();
        this.country = address.getCountry();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
