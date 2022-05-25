package com.example.demo.dto;

import java.time.LocalDate;

public class ReservationsFilterDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private String sortBy;
    private Boolean desc;

    public ReservationsFilterDTO() {}

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
