package com.example.demo.dto;

import com.example.demo.model.Client;

public class RegistrationDTO {
    private String email;
    private String password;
    private String passwordConfirmation;
    private String name;
    private String surname;
    private String street;
    private String city;
    private String country;
    private String tel;


    public RegistrationDTO()
    {

    }

    public RegistrationDTO(String email, String password, String passwordConfirmation, String name, String surname, String street, String city, String country, String tel) {
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.city = city;
        this.country = country;
        this.tel = tel;
    }

    public RegistrationDTO(Client c)
    {
        this.email = c.getEmail();
        this.password = c.getPassword();
        this.name = c.getName();
        this.surname = c.getSurname();
        this.street = c.getAddress().getStreet();
        this.city = c.getAddress().getCity();
        this.country = c.getAddress().getCountry();
        this.tel = c.getPhoneNumber();

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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}


