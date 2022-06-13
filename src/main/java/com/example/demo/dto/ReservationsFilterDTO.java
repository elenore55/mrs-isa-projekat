package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationsFilterDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String sortBy;
    private Boolean desc;

    public ReservationsFilterDTO() {
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        if (startDate == null) {
            startDate = LocalDateTime.MIN;
        }
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        if (endDate == null) {
            endDate = LocalDateTime.MAX;
        }
        this.endDate = endDate;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Boolean getDesc() {
        return desc;
    }

    public void setDesc(Boolean desc) {
        this.desc = desc;
    }
}
