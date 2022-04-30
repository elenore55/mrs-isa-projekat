package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.ProfileData;
import com.example.demo.model.User;
import com.example.demo.model.enums.Category;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.Profile_DataRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private Profile_DataRepository profileDataRepository;

    @Autowired
    public UserService(UserRepository userRepository, ClientRepository clientRepository, Profile_DataRepository profileDataRepository)
    {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.profileDataRepository = profileDataRepository;
    }

    public User save(User user){
        Client c = new Client();
        c.setProfileData(user.getProfileData());
        c.setNumberOfPoints(0);
        c.setCategory(Category.REGULAR);
        Client retC = clientRepository.save(c);
        return retC;
    }


    public String findUserToken(String email, String password) {
        ProfileData pd = profileDataRepository.getByEmail(email);
        if (pd==null) return "";
        if (isValidPassword(password, pd.getPassword())) return generateTokenById(pd.getId());
        return "";
    }

    private String generateTokenById(Integer id) {
        // ovdje sad nisam sigurna kako ide, zasad samo id
        return id.toString();
    }

    private boolean isValidPassword(String password, String password1) {
        // ovdje treba hesirati unijetu lozinku i vidjeti da li se poklapa sa onom iz baze
        return password.equals(password1);
    }

    public boolean isAlreadyRegistered(String email) {
        List<ProfileData> svi = profileDataRepository.findAll();
        for (ProfileData p : svi)
        {
            System.out.println(p.getEmail() + " je email redom");
        }
        ProfileData pd = profileDataRepository.getByEmail(email);
        return pd != null;
    }
}
