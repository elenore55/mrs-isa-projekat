package com.example.demo.repository;

import com.example.demo.model.FishingEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishingEquipmentRepository extends JpaRepository<FishingEquipment, Integer> {
}
