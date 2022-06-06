package com.example.demo.dto;

public class VisitReportDTO {
    private Integer offerId;
    private String offerName;
    private Integer daysVisited;

    public VisitReportDTO() {}

    public VisitReportDTO(Integer offerId, String offerName, Integer daysVisited) {
        this.offerId = offerId;
        this.offerName = offerName;
        this.daysVisited = daysVisited;
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

    public Integer getDaysVisited() {
        return daysVisited;
    }

    public void setDaysVisited(Integer daysVisited) {
        this.daysVisited = daysVisited;
    }
}
