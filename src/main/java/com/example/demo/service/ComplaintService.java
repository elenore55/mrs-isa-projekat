package com.example.demo.service;

import com.example.demo.model.Complaint;
import com.example.demo.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public List<Complaint> findAll(){return complaintRepository.findAll();}
    public Complaint findOne(Integer id){return complaintRepository.getById(id);}
    public Complaint update(Complaint complaint){return complaintRepository.save(complaint);}
}
