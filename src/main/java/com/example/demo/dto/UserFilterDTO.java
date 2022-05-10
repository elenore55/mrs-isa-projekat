package com.example.demo.dto;

import java.util.Date;
import java.util.List;

public class UserFilterDTO {
    private String fromDate;
    private String toDate;
    private String country;
    private String city;
    private int rate;
    private List<String> sortByList;
    private int sortBy;
    private int direction;

    public UserFilterDTO() {}



    public UserFilterDTO(String fromDate, String toDate, String country, String city, int rate, List<String> sortByList, int sortBy, int direction) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.country = country;
        this.city = city;
        this.rate = rate;
        this.sortByList = sortByList;
        this.sortBy = sortBy;
        this.direction = direction;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public List<String> getSortByList() {
        return sortByList;
    }

    public void setSortByList(List<String> sortByList) {
        this.sortByList = sortByList;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
