package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PriceList {
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal amount;

    public PriceList() {
    }

    public PriceList(LocalDate startDate, LocalDate endDate, BigDecimal amount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
