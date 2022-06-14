package com.example.demo.dto;

import java.math.BigDecimal;

public class ReportEntryDTO {
    private String x;
    private BigDecimal y;

    public ReportEntryDTO() {}

    public ReportEntryDTO(String x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }
}
