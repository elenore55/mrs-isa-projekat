package com.example.demo.repository;

import com.example.demo.model.FishingInstructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishingInstructorRepository extends JpaRepository<FishingInstructor, Integer> {
}
