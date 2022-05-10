package com.example.demo.dto;

import com.example.demo.model.Admin;

public class AdminDTO {
    private Boolean is_main;
    private Integer id;
    private ProfileDataDTO profileDataDTO;

    public AdminDTO() {

    }

    public AdminDTO(Boolean is_main, Integer id, ProfileDataDTO profileDataDTO) {
        this.is_main = is_main;
        this.id = id;
        this.profileDataDTO = profileDataDTO;
    }

    public AdminDTO(Admin admin)
    {
        this.id = admin.getId();
        this.is_main=admin.getMain();
        this.profileDataDTO = new ProfileDataDTO(admin.getProfileData());
    }

    public Boolean getIs_main() {
        return is_main;
    }

    public void setIs_main(Boolean is_main) {
        this.is_main = is_main;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProfileDataDTO getProfileDataDTO() {
        return profileDataDTO;
    }

    public void setProfileDataDTO(ProfileDataDTO profileDataDTO) {
        this.profileDataDTO = profileDataDTO;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "is_main=" + is_main +
                ", id=" + id +
                ", profileDataDTO=" + profileDataDTO +
                '}';
    }
}
