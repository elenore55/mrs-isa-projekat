package com.example.demo.repository;

import com.example.demo.model.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyRepository extends JpaRepository<Penalty,Integer> {
}
