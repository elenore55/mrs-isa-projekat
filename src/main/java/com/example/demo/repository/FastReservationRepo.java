package com.example.demo.repository;

import com.example.demo.model.FastReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FastReservationRepo extends JpaRepository<FastReservation, Integer> {
}
