package com.example.demo.model;

import com.example.demo.model.enums.AdminApprovalStatus;

import java.time.LocalDateTime;

public class DeletionRequest {
    private User user;
    private LocalDateTime dateTime;
    private AdminApprovalStatus status;

    public DeletionRequest() {

    }

    public DeletionRequest(User user, LocalDateTime dateTime, AdminApprovalStatus status) {
        this.user = user;
        this.dateTime = dateTime;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public AdminApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(AdminApprovalStatus status) {
        this.status = status;
    }
}
