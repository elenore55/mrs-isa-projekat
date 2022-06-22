package com.example.demo.model;

import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.InheritanceType.JOINED;

@Entity
@Table(name = "my_users")
@Inheritance(strategy = JOINED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    protected Integer numberOfPoints;

    @Enumerated(EnumType.STRING)
    protected Category category;

    @OneToOne(cascade = CascadeType.ALL)
    protected ProfileData profileData;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    protected Role role;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    public User() {
    }

    public User(String email, String password, String name, String surname, String phoneNumber, Address address) {
        // this.profileData = new ProfileData(email, password, name, surname, phoneNumber, address);
        this.numberOfPoints = 0;
        this.category = Category.REGULAR;
    }

    public User(RegistrationDTO registrationDTO) {
        Address a = new Address(registrationDTO.getStreet(), registrationDTO.getCity(), registrationDTO.getCountry());
        this.profileData = new ProfileData(registrationDTO.getEmail(), registrationDTO.getPassword(),
                registrationDTO.getName(), registrationDTO.getSurname(), registrationDTO.getPhone(), a);
        this.numberOfPoints = 0;
        this.category = Category.REGULAR;

    }

    public User(ProfileData profileData) {
        this.profileData = profileData;
        this.numberOfPoints = 0;
        this.category = Category.REGULAR;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return profileData.getEmail();
    }

    public void setEmail(String email) {
        profileData.setEmail(email);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auth = new ArrayList<>();
        auth.add(role);
        return auth;
    }

    public String getPassword() {
        return profileData.getPassword();
    }

    @Override
    public String getUsername() {
        return profileData.getEmail();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}
