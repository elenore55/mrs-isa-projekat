package com.example.demo.service;

import com.example.demo.model.DeletionRequest;
import com.example.demo.model.User;
import com.example.demo.repository.DeletionRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletionRequestService {
    private final DeletionRequestRepository deletionRequestRepository;
    private final UserRepository userRepository;

    @Autowired
    public DeletionRequestService(DeletionRequestRepository deletionRequestRepository, UserRepository userRepository)
    {
        this.deletionRequestRepository = deletionRequestRepository;
        this.userRepository = userRepository;
    }

    public DeletionRequest save(DeletionRequest deletionRequest)
    {
        return deletionRequestRepository.save(deletionRequest);
    }

    public User findById(String id) {
        int n = Integer.parseInt(id);
        return userRepository.getById(n);
    }
}
