package com.example.demo.dto;

import com.example.demo.model.FishingInstructor;

public class FishingInstructorDTO {
    private Integer id;
    private String biography;
    //lombokPOGLEDAJ!!!
    private ProfileDataDTO profileDataDTO;

    public FishingInstructorDTO()
    {

    }
    public FishingInstructorDTO(Integer id, String biography, ProfileDataDTO profileDataDTO)
    {
        this.id = id;
        this.biography=biography;
        this.profileDataDTO = profileDataDTO;
    }

    public FishingInstructorDTO(FishingInstructor fishingInstructor){
        this.id=fishingInstructor.getId();
        this.biography = fishingInstructor.getBiography();
        this.profileDataDTO = new ProfileDataDTO(fishingInstructor.getProfileData());

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

    public ProfileDataDTO getProfileDataDTO() {
        return profileDataDTO;
    }

    public void setProfileDataDTO(ProfileDataDTO profileDataDTO) {
        this.profileDataDTO = profileDataDTO;
    }

    @Override
    public String toString() {
        return "FishingInstructorDTO{" +
                "id=" + id +
                ", biography='" + biography + '\'' +
                ", profileDataDTO=" + profileDataDTO +
                '}';
    }
}
