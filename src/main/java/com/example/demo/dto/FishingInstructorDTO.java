package com.example.demo.dto;

import com.example.demo.model.FishingInstructor;

public class FishingInstructorDTO {
    private Integer id;
    private String biography;

    public FishingInstructorDTO()
    {

    }

    public FishingInstructorDTO(Integer id, String biography) {
        this.id = id;
        this.biography = biography;
    }

    public FishingInstructorDTO(FishingInstructor fishingInstructor){
        this.id=fishingInstructor.getId();
        this.biography = fishingInstructor.getBiography();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
