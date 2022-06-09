package com.example.demo.service;

import com.example.demo.model.ClientReview;
import com.example.demo.repository.ClientReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientReviewService {
    private ClientReviewRepository repository;

    @Autowired
    public ClientReviewService(ClientReviewRepository repository) {
        this.repository = repository;
    }

    public ClientReview findOne(Integer id) {
        return repository.findById(id).orElseGet(null);
    }

    public ClientReview save(ClientReview clientReview) {
        return this.repository.save(clientReview);
    }
}
