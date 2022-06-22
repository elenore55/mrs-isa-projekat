package com.example.demo.service;

import com.example.demo.dto.ChangePasswordDTO;
import com.example.demo.dto.EditProfileDTO;
import com.example.demo.model.*;
import com.example.demo.model.enums.Category;
import com.example.demo.model.enums.RegistrationType;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private FishingInstructorRepository fishingInstructorRepository;
    private CottageOwnerRepository cottageOwnerRepository;
    private ShipOwnerRepository shipOwnerRepository;
    private Profile_DataRepository profileDataRepository;
    private AddressRepository addressRepository;

    @Autowired
    public UserService(UserRepository userRepository, ClientRepository clientRepository, FishingInstructorRepository fishingInstructorRepository, CottageOwnerRepository cottageOwnerRepository, ShipOwnerRepository shipOwnerRepository, Profile_DataRepository profileDataRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.fishingInstructorRepository = fishingInstructorRepository;
        this.cottageOwnerRepository = cottageOwnerRepository;
        this.shipOwnerRepository = shipOwnerRepository;
        this.profileDataRepository = profileDataRepository;
        this.addressRepository = addressRepository;
    }
//    public UserService(UserRepository userRepository, ClientRepository clientRepository,
//                       Profile_DataRepository profileDataRepository, AddressRepository addressRepository) {
//        this.userRepository = userRepository;
//        this.clientRepository = clientRepository;
//        this.profileDataRepository = profileDataRepository;
//        this.addressRepository = addressRepository;
//    }


    public User save(User user){
        Client c = new Client();
        c.setProfileData(user.getProfileData());
        c.setNumberOfPoints(0);
        c.setCategory(Category.REGULAR);
        Client retC = clientRepository.save(c);
        return retC;
    }

    public void saveUSER(User user){
        userRepository.save(user);
    }

    public User saveOwners(ProfileData profileData, RegistrationType type){
        ProfileData temp = new ProfileData(profileData);
        if(type== RegistrationType.COTTAGE_OWNER)
        {
            CottageOwner cottageOwner = new CottageOwner();
            cottageOwner.setProfileData(temp);
            cottageOwner.setNumberOfPoints(0);
            cottageOwner.setCategory(Category.REGULAR);
            CottageOwner res = cottageOwnerRepository.save(cottageOwner);
            return res;
        }
        else if(type== RegistrationType.SHIP_OWNER)
        {
            ShipOwner shipOwner = new ShipOwner();
            shipOwner.setProfileData(temp);
            shipOwner.setNumberOfPoints(0);
            shipOwner.setCategory(Category.REGULAR);
            ShipOwner res = shipOwnerRepository.save(shipOwner);
            return res;
        }
        else if(type== RegistrationType.FISHING_OWNER)
        {
            FishingInstructor fishingInstructor = new FishingInstructor();
            fishingInstructor.setProfileData(temp);
            fishingInstructor.setNumberOfPoints(0);
            fishingInstructor.setCategory(Category.REGULAR);
            FishingInstructor res = fishingInstructorRepository.save(fishingInstructor);
            return res;
        }
        return null;
    }

    public String findUserToken(String email, String password) {
        ProfileData pd = profileDataRepository.getByEmail(email);
        if (pd == null) return "";
        if (isValidPassword(password, pd.getPassword())) return generateTokenById(pd.getId());
        return "";
    }

    /*public Client findClientByEmail(String email) {
        ProfileData pd = profileDataRepository.getByEmail(email);
        if (pd == null) return null;
        return clientRepository.findByProfileDataId(pd.getId());
    }*/

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
        List<ProfileData> li = profileDataRepository.findAll();

        ProfileData pd = profileDataRepository.getByEmail(email);
        if (pd == null) return null;
        return clientRepository.findByProfileDataId(pd.getId());
    }

    public User findUserByEmailForAdmin(String email) {
        ProfileData pd = profileDataRepository.getByEmail(email);
        if (pd == null) return null;
        return userRepository.findByProfileDataId(pd.getId());
    }


    public User findOne(Integer id) {
        return userRepository.findById(id).orElseGet(null);
    }
    public List<User> findAll() { return userRepository.findAll();}

    public void addReservation(Integer id, Reservation reservation) {
        User user = findOne(id);
        if (user instanceof CottageOwner) {
            CottageOwner c = (CottageOwner)user;
            List<Reservation> reservations = c.getReservations();
            reservations.add(reservation);
            c.setReservations(reservations);
            userRepository.save(c);
        } else if (user instanceof ShipOwner) {
            ShipOwner c = (ShipOwner)user;
            List<Reservation> reservations = c.getReservations();
            reservations.add(reservation);
            c.setReservations(reservations);
            userRepository.save(c);
        } else if (user instanceof FishingInstructor) {
            FishingInstructor fi = (FishingInstructor) user;
            List<Reservation> reservations = fi.getReservations();
            reservations.add(reservation);
            fi.setReservations(reservations);
            userRepository.save(fi);
        }
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseGet(null);
    }

    public void unfollow(Client c) {
        clientRepository.save(c);
    }

    public void remove(Integer id) {
        userRepository.deleteById(id);
    }
}
