package com.example.demo.repository;

import com.example.demo.model.CottageOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CottageOwnerRepository extends JpaRepository<CottageOwner, Integer> {
}
