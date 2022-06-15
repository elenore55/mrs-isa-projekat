package com.example.demo.model;

import com.example.demo.model.enums.AdminApprovalStatus;

import javax.persistence.*;
import java.lang.ref.Cleaner;
import java.time.LocalDateTime;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "complaint_date_time")
    private LocalDateTime dateTime;

    @Column
    private String content;

    @Enumerated(EnumType.STRING)
    private AdminApprovalStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client issuedBy;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(Client issuedBy) {
        this.issuedBy = issuedBy;
    }
}
