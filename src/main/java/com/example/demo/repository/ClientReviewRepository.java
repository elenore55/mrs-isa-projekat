package com.example.demo.repository;

import com.example.demo.model.ClientReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientReviewRepository extends JpaRepository<ClientReview, Integer> {
}
