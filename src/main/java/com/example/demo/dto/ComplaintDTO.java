package com.example.demo.dto;

public class ComplaintDTO {
    private String content;
    private int id;

    public ComplaintDTO(){}

    public ComplaintDTO(String content, int id) {
        this.content = content;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


