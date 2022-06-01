package com.example.demo.dto;

import com.example.demo.model.Offer;

public class SubDTO {
    private String name;
    private Integer id;
    private String image;
    private String description;
    private AddressDTO address;

    public SubDTO()    {  }

    public SubDTO(String name, Integer id, String image, String description, AddressDTO address) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.description = description;
        this.address = address;
    }

    public SubDTO(Offer o) {
        this.name = o.getName();
        this.address = new AddressDTO();
        this.address.setCountry(o.getAddress().getCountry());
        this.address.setCity(o.getAddress().getCity());
        this.address.setStreet(o.getAddress().getStreet());
        this.description = o.getDescription();
        this.id = o.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
