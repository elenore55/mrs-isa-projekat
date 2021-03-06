package com.example.demo.model;

import com.example.demo.dto.ProfileDataDTO;

import javax.persistence.*;

@Entity
public class ProfileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public ProfileData() {
    }

    public ProfileData(String email, String password, String name, String surname, String phoneNumber, Address address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public  ProfileData(ProfileDataDTO profileDataDTO)
    {
        this.email = profileDataDTO.getEmail();
        this.password = profileDataDTO.getPassword();
        this.name = profileDataDTO.getName();
        this.surname = profileDataDTO.getSurname();
        this.phoneNumber = profileDataDTO.getPhoneNumber();
        this.address = profileDataDTO.getAddress();
    }

    public  ProfileData(ProfileData profileData)
    {
        this.email = profileData.getEmail();
        this.password = profileData.getPassword();
        this.name = profileData.getName();
        this.surname = profileData.getSurname();
        this.phoneNumber = profileData.getPhoneNumber();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
