package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ClientReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "complaint_date_time")
    private LocalDateTime dateTime;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private User issuedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @OneToOne(mappedBy = "clientReview")
    private Reservation reservation;

    @Column
    private Boolean penaltyRequested;

    public ClientReview() {
    }

    public ClientReview(LocalDateTime dateTime, String content, User issuedBy, Client client, Boolean penaltyRequested) {
        this.dateTime = dateTime;
        this.content = content;
        this.issuedBy = issuedBy;
        this.client = client;
        this.penaltyRequested = penaltyRequested;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(User issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Boolean getPenaltyRequested() {
        return penaltyRequested;
    }

    public void setPenaltyRequested(Boolean penaltyRequested) {
        this.penaltyRequested = penaltyRequested;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
