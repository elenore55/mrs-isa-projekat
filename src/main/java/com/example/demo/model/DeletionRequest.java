package com.example.demo.model;

import com.example.demo.model.enums.AdminApprovalStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DeletionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User sentBy;

    @Column(name = "request_date_time")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private AdminApprovalStatus status;

    public DeletionRequest() {
    }

    public DeletionRequest(User user, LocalDateTime dateTime, AdminApprovalStatus status) {
        this.sentBy = user;
        this.dateTime = dateTime;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSentBy() {
        return sentBy;
    }

    public void setSentBy(User sentBy) {
        this.sentBy = sentBy;
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
