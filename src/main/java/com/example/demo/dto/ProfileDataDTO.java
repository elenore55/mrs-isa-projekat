package com.example.demo.dto;

import com.example.demo.model.Address;
import com.example.demo.model.ProfileData;

public class ProfileDataDTO {
    private Integer id;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    private String surname;
    private Address address;

    public ProfileDataDTO()
    {

    }

    public ProfileDataDTO(ProfileData profileData)
    {
        this.id = profileData.getId();
        this.email = profileData.getEmail();
        this.name = profileData.getName();
        this.password = profileData.getPassword();
        this.phoneNumber = profileData.getPhoneNumber();
        this.surname = profileData.getSurname();
        this.address = profileData.getAddress();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
