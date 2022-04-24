package com.example.demo.repository;

import com.example.demo.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Integer> {
}
