package com.example.demo.dto;

import java.math.BigDecimal;

public class IncomeReportDTO {
    private Integer offerId;
    private String offerName;
    private BigDecimal income;

    public IncomeReportDTO() {
    }

    public IncomeReportDTO(Integer offerId, String offerName, BigDecimal income) {
        this.offerId = offerId;
        this.offerName = offerName;
        this.income = income;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
