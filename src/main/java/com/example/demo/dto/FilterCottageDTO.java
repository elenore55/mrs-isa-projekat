package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public class FilterCottageDTO {
    private List<String> cities;
    private List<String> countries;
    private Integer low;
    private Integer high;
    private String sortParam;
    private String sortDir;

    public FilterCottageDTO() {}

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

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
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
        if (cities.size() == 0) return true;
        return cities.contains(city);
    }

    public boolean checkCountry(String country) {
        if (countries.size() == 0) return true;
        return countries.contains(country);
    }

    public boolean checkPrice(BigDecimal price) {
        return checkLow(price) && checkHigh(price);
    }

    public boolean checkLow(BigDecimal price) {
        if (low == null) return true;
        return price.compareTo(BigDecimal.valueOf(low)) >= 0;
    }

    public boolean checkHigh(BigDecimal price) {
        if (high == null || high == 0) return true;
        return price.compareTo(BigDecimal.valueOf(high)) <= 0;
    }
}
