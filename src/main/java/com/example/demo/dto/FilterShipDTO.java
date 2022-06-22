package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public class FilterShipDTO {
    private List<String> cities;
    private List<String> countries;
    private Integer lowPrice;
    private Integer highPrice;
    private Integer lowLength;
    private Integer highLength;
    private Integer lowCapacity;
    private Integer highCapacity;
    private Integer lowSpeed;
    private Integer highSpeed;
    private String sortParam;
    private String sortDir;

    public FilterShipDTO() {}

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public Integer getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }

    public Integer getLowLength() {
        return lowLength;
    }

    public void setLowLength(Integer lowLength) {
        this.lowLength = lowLength;
    }

    public Integer getHighLength() {
        return highLength;
    }

    public void setHighLength(Integer highLength) {
        this.highLength = highLength;
    }

    public Integer getLowCapacity() {
        return lowCapacity;
    }

    public void setLowCapacity(Integer lowCapacity) {
        this.lowCapacity = lowCapacity;
    }

    public Integer getHighCapacity() {
        return highCapacity;
    }

    public void setHighCapacity(Integer highCapacity) {
        this.highCapacity = highCapacity;
    }

    public Integer getLowSpeed() {
        return lowSpeed;
    }

    public void setLowSpeed(Integer lowSpeed) {
        this.lowSpeed = lowSpeed;
    }

    public Integer getHighSpeed() {
        return highSpeed;
    }

    public void setHighSpeed(Integer highSpeed) {
        this.highSpeed = highSpeed;
    }

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }

    public boolean checkCity(String city) {
        if (cities == null || cities.size() == 0) return true;
        return cities.contains(city);
    }

    public boolean checkCountry(String country) {
        if (countries == null || countries.size() == 0) return true;
        return countries.contains(country);
    }

    public boolean checkPrice(BigDecimal price) {
        return checkLow(price, lowPrice) && checkHigh(price, highPrice);
    }

    public boolean checkLength(Double length) {
        return checkLow(BigDecimal.valueOf(length), lowLength) && checkHigh(BigDecimal.valueOf(length), highLength);
    }

    public boolean checkCapacity(Integer capacity) {
        return checkLow(BigDecimal.valueOf(capacity), lowCapacity) && checkHigh(BigDecimal.valueOf(capacity), highCapacity);
    }

    public boolean checkSpeed(Integer speed) {
        return checkLow(BigDecimal.valueOf(speed), lowSpeed) && checkHigh(BigDecimal.valueOf(speed), highSpeed);
    }

    private boolean checkLow(BigDecimal value, Integer low) {
        if (low == null) return true;
        return value.compareTo(BigDecimal.valueOf(low)) >= 0;
    }

    private boolean checkHigh(BigDecimal value, Integer high) {
        if (high == null) return true;
        return value.compareTo(BigDecimal.valueOf(high)) <= 0;
    }
}
