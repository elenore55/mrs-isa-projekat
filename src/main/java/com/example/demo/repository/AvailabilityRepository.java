package com.example.demo.repository;

import com.example.demo.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
}
