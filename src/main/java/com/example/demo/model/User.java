package com.example.demo.model;

import com.example.demo.model.enums.Category;

public abstract class User {
    private ProfileData profileData;
    private Integer numberOfPoints;
    private Category category;

    public User() {
    }

    public User(String email, String password, String name, String surname, String phoneNumber, Address address) {
        this.profileData = new ProfileData(email, password, name, surname, phoneNumber, address);
        this.numberOfPoints = 0;
        this.category = Category.REGULAR;
    }

    public String getEmail() {
        return profileData.getEmail();
    }

    public void setEmail(String email) {
        profileData.setEmail(email);
    }

    public String getPassword() {
        return profileData.getPassword();
    }

    public void setPassword(String password) {
        profileData.setPassword(password);
    }

    public String getName() {
        return profileData.getName();
    }

    public void setName(String name) {
        profileData.setName(name);
    }

    public String getSurname() {
        return profileData.getSurname();
    }

    public void setSurname(String surname) {
        profileData.setSurname(surname);
    }

    public Address getAddress() {
        return profileData.getAddress();
    }

    public void setAddress(Address address) {
        profileData.setAddress(address);
    }

    public String getPhoneNumber() {
        return profileData.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        profileData.setPhoneNumber(phoneNumber);
    }

    public ProfileData getProfileData() {
        return profileData;
    }

    public void setProfileData(ProfileData profileData) {
        this.profileData = profileData;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
