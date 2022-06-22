package com.example.demo.service;

import com.example.demo.model.Complaint;
import com.example.demo.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;


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

    public Complaint findOne(Integer id){return this.repository.getById(id);}
    public Complaint update(Complaint complaint){return this.repository.save(complaint);}
}
