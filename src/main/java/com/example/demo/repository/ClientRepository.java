package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("select c from Client c where c.profileData.id = ?1")
    Client findByProfileDataId(Integer pdId);

}