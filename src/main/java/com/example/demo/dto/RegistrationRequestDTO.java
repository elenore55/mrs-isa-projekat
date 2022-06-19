package com.example.demo.dto;

import com.example.demo.model.RegistrationRequest;
import com.example.demo.model.enums.RegistrationType;

public class RegistrationRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private AddressDTO address;
    private String phoneNumber;
    private String type;
    private String reason;

    public RegistrationRequestDTO() {
    }

    public RegistrationRequestDTO(RegistrationRequest r) {
        this.name = r.getProfileData().getName();
        this.surname = r.getProfileData().getSurname();
        this.email = r.getProfileData().getEmail();
        this.password = r.getProfileData().getPassword();
        this.address = new AddressDTO(r.getProfileData().getAddress());
        this.phoneNumber = r.getProfileData().getPhoneNumber();
        if (r.getRegistrationType() == RegistrationType.COTTAGE_OWNER) {
            this.type = "Cottage owner";
        } else if (r.getRegistrationType() == RegistrationType.SHIP_OWNER) {
            this.type = "Ship owner";
        }
        else if (r.getRegistrationType() == RegistrationType.FISHING_OWNER) {
            this.type = "Fishing owner";
        }
        this.reason = r.getReason();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RegistrationType getRegistrationType() {
        if (type.equalsIgnoreCase("Cottage owner"))
            return RegistrationType.COTTAGE_OWNER;
        else if(type.equalsIgnoreCase("Ship owner"))
            return RegistrationType.SHIP_OWNER;
        else if(type.equalsIgnoreCase("Fishing owner"))
            return RegistrationType.FISHING_OWNER;
        return null;
    }
}
