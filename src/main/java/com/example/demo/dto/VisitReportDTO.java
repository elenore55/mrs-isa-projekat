package com.example.demo.dto;

public class VisitReportDTO {
    private Integer offerId;
    private String offerName;
    private Long daysVisited;

    public VisitReportDTO() {}

    public VisitReportDTO(Integer offerId, String offerName, Long daysVisited) {
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

    public long getDaysVisited() {
        return daysVisited;
    }

    public void setDaysVisited(Long daysVisited) {
        this.daysVisited = daysVisited;
    }
}
