package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    //@Query("select u from user u where u.email = ?1")
    //public List<User> findByEmail(String email);

    //@Query("select u from user u where u.name = ?1")
    //public User findOneByEmail(String email);
}