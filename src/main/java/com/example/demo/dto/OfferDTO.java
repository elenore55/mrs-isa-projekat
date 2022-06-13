package com.example.demo.dto;

import com.example.demo.model.Address;
import com.example.demo.model.Offer;

public class OfferDTO {
    private Integer id;
    private String name;
    private Address address;
    private String description;

    public OfferDTO()
    {

    }

    public OfferDTO(Integer id, String name, Address address, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public OfferDTO(Offer offer)
    {
        this.id = offer.getId();
        this.name = offer.getName();
        this.address = offer.getAddress();
        this.description = offer.getDescription();
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

    @Override
    public String toString() {
        return "OfferDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", description='" + description + '\'' +
                '}';
    }
}
