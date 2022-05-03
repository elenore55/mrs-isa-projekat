package com.example.demo.repository;

import com.example.demo.model.DeletionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletionRequestRepository extends JpaRepository<DeletionRequest, Integer> {
}
