package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.ProfileData;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.profileData.id = ?1")
    User findByProfileDataId(Integer pdId);
}