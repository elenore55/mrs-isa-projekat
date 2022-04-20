package com.example.demo.dto;

import com.example.demo.model.FishingInstructor;

public class FishingInstructorDTO {
    private Integer id;
    private String biography;
//    private String email;
//    private String password;
//    private String name;
//    private String surname;
//    private String phoneNumber;
//    private AddressDTO address; //lombokPOGLEDAJ!!!
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


//    public FishingInstructorDTO(Integer id, String biography, String email, String password, String name, String surname, String phoneNumber, AddressDTO address) {
//        this.id = id;
//        this.biography = biography;
//        this.email = email;
//        this.password = password;
//        this.name = name;
//        this.surname = surname;
//        this.phoneNumber = phoneNumber;
//        this.address = address;
//    }
//
    public FishingInstructorDTO(FishingInstructor fishingInstructor){
        this.id=fishingInstructor.getId();
        this.biography = fishingInstructor.getBiography();
        this.profileDataDTO = new ProfileDataDTO(fishingInstructor.getProfileData());
//        this.email = fishingInstructor.getEmail();
//        this.password = fishingInstructor.getPassword();
//        this.name = fishingInstructor.getName();
//        this.surname = fishingInstructor.getSurname();
//        this.phoneNumber = fishingInstructor.getPhoneNumber();
//        this.address = new AddressDTO(fishingInstructor.getAddress());
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public AddressDTO getAddress() {
//        return address;
//    }
//
//    public void setAddress(AddressDTO address) {
//        this.address = address;
//    }

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
