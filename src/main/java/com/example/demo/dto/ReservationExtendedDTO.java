package com.example.demo.dto;

import com.example.demo.model.Reservation;
import com.example.demo.model.enums.ReservationStatus;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class ReservationExtendedDTO{
    private Integer id;
    private String clientEmail;
    private Integer offerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isInThreeDays;
    private long price;
    private ReservationStatus status;
    private String link;
    private String name;

    public ReservationExtendedDTO() {}

    public ReservationExtendedDTO(Reservation r) {
        this.id = r.getId();
        if (r.getClient() != null)
            this.clientEmail = r.getClient().getEmail();
        this.offerId = r.getOffer().getId();
        this.startDate = r.getStart();
        this.endDate =  r.getEnd();
        this.status = r.getReservationStatus();
        this.isInThreeDays = checkIfIsInTheeDays();
        this.price = calculatePrice(r);
        this.link = createLink();
    }

    private String createLink() {
        String link = "#cottageDetailedView/" + this.offerId + "/" + this.startDate + "/" + this.endDate;
        return link;
    }

    private long calculatePrice(Reservation r) {
        LocalDateTime d1 = this.getStartDate();
        LocalDateTime d2 = this.getEndDate();
        long daysBetween = Duration.between(d1, d2).toDays();
        long price = r.getOffer().getPriceList().longValue() * daysBetween;
        return price;
    }

    private boolean checkIfIsInTheeDays() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime d = this.getStartDate();
        long daysBetween = Duration.between(now, d).toDays();
        System.out.println("Razmak izmedju jeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee " + daysBetween);
        System.out.println(daysBetween<3);
        return daysBetween < 3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isInThreeDays() {
        return isInThreeDays;
    }

    public void setInThreeDays(boolean inThreeDays) {
        isInThreeDays = inThreeDays;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
