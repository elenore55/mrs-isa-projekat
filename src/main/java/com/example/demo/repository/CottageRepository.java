package com.example.demo.repository;

import com.example.demo.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CottageRepository extends JpaRepository<Cottage, Integer> {}