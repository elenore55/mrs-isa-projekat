package com.example.demo.dto;

import com.example.demo.model.RegistrationRequest;
import com.example.demo.model.enums.RegistrationType;

public class RegistrationRequestAdminDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private AddressDTO address;
    private String phoneNumber;
    private RegistrationType type;
    private String reason;
    private Integer id;

    public RegistrationRequestAdminDTO(String name, String surname, String email, String password, AddressDTO address, String phoneNumber, RegistrationType type, String reason, Integer id) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.reason = reason;
        this.id = id;
    }

    public RegistrationRequestAdminDTO(RegistrationRequest request) {
        this.name = request.getProfileData().getName();
        this.surname = request.getProfileData().getSurname();
        this.email = request.getProfileData().getEmail();
        this.password = request.getProfileData().getPassword();
        this.address = new AddressDTO(request.getProfileData().getAddress());
        this.phoneNumber = request.getProfileData().getPhoneNumber();
        this.type = request.getRegistrationType();
        this.reason = request.getReason();
        this.id = request.getId();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RegistrationType getType() {
        return type;
    }

    public void setType(RegistrationType type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
