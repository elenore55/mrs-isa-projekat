package com.example.demo.model;

import com.example.demo.model.enums.AdminApprovalStatus;

import java.time.LocalDateTime;

public class Complaint {
    private LocalDateTime dateTime;
    private String content;
    private AdminApprovalStatus status;

    public Complaint() {

    }

    public Complaint(LocalDateTime dateTime, String content, AdminApprovalStatus status) {
        this.dateTime = dateTime;
        this.content = content;
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AdminApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(AdminApprovalStatus status) {
        this.status = status;
    }
}
