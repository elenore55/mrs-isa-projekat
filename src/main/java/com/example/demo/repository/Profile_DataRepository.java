package com.example.demo.repository;

import com.example.demo.model.ProfileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface Profile_DataRepository extends JpaRepository<ProfileData, Integer> {

    @Query("select p from ProfileData p where p.email = ?1")
    public ProfileData getByEmail(String email);

    @Modifying
    @Query("update ProfileData p set p.name = ?1 where p.email = ?2")
    public void updateName(String name, String email);

    @Modifying
    @Query("update ProfileData p set p.surname = ?1 where p.email = ?2")
    public void updateSurname(String surname, String email);

    @Modifying
    @Query("update ProfileData p set p.phoneNumber = ?1 where p.email = ?2")
    public void updatePhone(String phone, String email);

    @Modifying
    @Query("update ProfileData p set p.name = ?1, p.surname = ?2, p.phoneNumber = ?3  where p.email = ?4")
    public void updateBasicData(String name, String surname, String phone, String email);

    @Query("select p.id from ProfileData p where p.email = ?1")
    int getAddressByEmail(String email);

    @Modifying
    @Query("update ProfileData p set p.password = ?1 where p.id = ?2")
    public void changePassword(String newPass, int id);
}
