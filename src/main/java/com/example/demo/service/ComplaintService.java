package com.example.demo.service;

import com.example.demo.model.Complaint;
import com.example.demo.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
public class ComplaintService {
    private ComplaintRepository repository;

    @Autowired
    public ComplaintService(ComplaintRepository repository){
        this.repository = repository;
    }

    @Transactional
    public void save(Complaint c) {
        this.repository.save(c);
    }

    public List<Complaint> findAll() {
        return this.repository.findAll();
    }
}
