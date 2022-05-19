package com.example.demo.service;

import com.example.demo.dto.ChangePasswordDTO;
import com.example.demo.dto.EditProfileDTO;
import com.example.demo.model.Client;
import com.example.demo.model.ProfileData;
import com.example.demo.model.User;
import com.example.demo.model.enums.Category;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.Profile_DataRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private Profile_DataRepository profileDataRepository;
    private AddressRepository addressRepository;

    @Autowired
    public UserService(UserRepository userRepository, ClientRepository clientRepository,
                       Profile_DataRepository profileDataRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.profileDataRepository = profileDataRepository;
        this.addressRepository = addressRepository;
    }

    public User save(User user) {
        Client c = new Client();
        c.setProfileData(user.getProfileData());
        c.setNumberOfPoints(0);
        c.setCategory(Category.REGULAR);
        Client retC = clientRepository.save(c);
        return retC;
    }


    public String findUserToken(String email, String password) {
        ProfileData pd = profileDataRepository.getByEmail(email);
        if (pd == null) return "";
        if (isValidPassword(password, pd.getPassword())) return generateTokenById(pd.getId());
        return "";
    }

    private String generateTokenById(Integer id) {
        // ovdje sad nisam sigurna kako ide, zasad samo id
        return id.toString();
    }

    private boolean isValidPassword(String password, String tabelar) {
        // ovdje treba hesirati unijetu lozinku i vidjeti da li se poklapa sa ovom iz tabele
        return password.equals(tabelar);
    }

    public boolean isAlreadyRegistered(String email) {
        List<ProfileData> svi = profileDataRepository.findAll();
        ProfileData pd = profileDataRepository.getByEmail(email);
        return pd != null;
    }

    public void updateProfileData(EditProfileDTO editProfileDTO) {
        profileDataRepository.updateBasicData(editProfileDTO.getName(), editProfileDTO.getSurname(), editProfileDTO.getPhone(), editProfileDTO.getEmail());
        int addressId = profileDataRepository.getAddressByEmail(editProfileDTO.getEmail());
        addressRepository.updateAddress(editProfileDTO.getStreet(), editProfileDTO.getCity(), editProfileDTO.getCountry(), addressId);
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        // sad smo sve validirali i treba samo da upisemo novu lozinku na odgovarajuce mjesto
        String hashed = changePasswordDTO.getNewPass();     // ovdje usmjesto da je samo preuzemem, treba i da je hesiram
        System.out.println("dobro je i uzeta lozinka prije uspisa i  glasi " + hashed);
        int n = Integer.parseInt(changePasswordDTO.getId());
        profileDataRepository.changePassword(hashed, n);
    }

    public boolean isUsersPassword(String old, String id) {
        String currentPassword = userRepository.getById(Integer.parseInt(id)).getPassword();    // ovo nam je dalo trenutnu hesiranu lozinku
        return isValidPassword(old, currentPassword);
    }

    public Client findClientByEmail(String email) {
        ProfileData pd = profileDataRepository.getByEmail(email);
        if (pd == null) return null;
        return clientRepository.findByProfileDataId(pd.getId());
    }

    public User findOne(Integer id) {
        return userRepository.findById(id).orElseGet(null);
    }
}
