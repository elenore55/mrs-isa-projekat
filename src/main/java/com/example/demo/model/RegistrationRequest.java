package com.example.demo.model;

import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.model.enums.RegistrationType;

import java.time.LocalDateTime;

public class RegistrationRequest {
    private LocalDateTime dateTime;
    private ProfileData profileData;
    private String reason;
    private RegistrationType registrationType;
    private AdminApprovalStatus status;

    public RegistrationRequest() {
    }

    public RegistrationRequest(LocalDateTime dateTime, ProfileData profileData, String reason, RegistrationType registrationType) {
        this.dateTime = dateTime;
        this.profileData = profileData;
        this.reason = reason;
        this.registrationType = registrationType;
        this.status = AdminApprovalStatus.PENDING;
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

    public AdminApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(AdminApprovalStatus status) {
        this.status = status;
    }
}
