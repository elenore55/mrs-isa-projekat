package com.example.demo.repository;

import com.example.demo.model.ProfileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Profile_DataRepository extends JpaRepository<ProfileData, Integer> {
    @Query("select p from ProfileData p where p.email = ?1")
    public ProfileData getByEmail(String email);
}
