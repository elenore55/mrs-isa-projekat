package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {


}