package com.example.demo.repository;

import com.example.demo.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureRepository extends JpaRepository<Adventure, Integer> {
}
