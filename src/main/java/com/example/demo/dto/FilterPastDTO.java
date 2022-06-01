package com.example.demo.dto;

public class FilterPastDTO {
    private int sortEntity;
    private int sortBy;
    private int id;

    public FilterPastDTO()  { }

    public FilterPastDTO(int sortEntity, int sortBy, int id) {
        this.sortEntity = sortEntity;
        this.sortBy = sortBy;
        this.id = id;
    }

    public int getSortEntity() {
        return sortEntity;
    }

    public void setSortEntity(int sortEntity) {
        this.sortEntity = sortEntity;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
