package com.example.demo.model;

import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.model.enums.RegistrationType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RegistrationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @OneToOne(cascade = CascadeType.ALL)
    private ProfileData profileData;

    @Column
    private String reason;

    @Enumerated(EnumType.STRING)
    private AdminApprovalStatus approvalStatus;

    @Enumerated(EnumType.STRING)
    private RegistrationType registrationType;

    @Version
    private Integer version;

    public RegistrationRequest() {
    }

    public RegistrationRequest(LocalDateTime dateTime, ProfileData profileData, String reason, RegistrationType registrationType) {
        this.dateTime = dateTime;
        this.profileData = profileData;
        this.reason = reason;
        this.registrationType = registrationType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ProfileData getProfileData() {
        return profileData;
    }

    public void setProfileData(ProfileData profileData) {
        this.profileData = profileData;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
    }

    public AdminApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(AdminApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
