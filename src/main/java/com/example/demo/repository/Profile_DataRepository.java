package com.example.demo.repository;

import com.example.demo.model.ProfileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Profile_DataRepository extends JpaRepository<ProfileData, Integer> {
}
