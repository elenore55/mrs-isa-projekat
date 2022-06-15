package com.example.demo.service;

import com.example.demo.model.Complaint;
import com.example.demo.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {
    private ComplaintRepository repository;

    @Autowired
    public ComplaintService(ComplaintRepository repository){
        this.repository = repository;

    }

    public void save(Complaint c) {
        this.repository.save(c);
    }
}