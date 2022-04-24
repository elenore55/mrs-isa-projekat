package com.example.demo.repository;

import com.example.demo.model.ShipOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipOwnerRepository extends JpaRepository<ShipOwner, Integer> {
}
