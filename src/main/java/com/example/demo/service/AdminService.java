package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin findOne(Integer id){ return adminRepository.getById(id);}
    public List<Admin> findAll() {return adminRepository.findAll();}
    public Admin save(Admin admin){
        return adminRepository.save(admin);
    }

    public void remove(Integer id) { adminRepository.deleteById(id);}
}