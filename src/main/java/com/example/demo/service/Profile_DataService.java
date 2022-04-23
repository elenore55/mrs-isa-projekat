package com.example.demo.service;

import com.example.demo.model.ProfileData;
import com.example.demo.repository.Profile_DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Profile_DataService {
    @Autowired
    private Profile_DataRepository profile_dataREpository;

    public ProfileData findOne(Integer id) {
        return profile_dataREpository.getById(id);
    }

    public ProfileData save(ProfileData profileData){
        return profile_dataREpository.save(profileData);
    }

    public void remove(Integer id) {
        profile_dataREpository.deleteById(id);
    }

}
