package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.RegistrationRequestRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationRequestService {
    private RegistrationRequestRepository repository;
    private AdminRepository adminRepository;
    private EmailSender emailSender;

    @Autowired
    public RegistrationRequestService(RegistrationRequestRepository repository, AdminRepository adminRepository, EmailSender emailSender) {
        this.repository = repository;
        this.adminRepository = adminRepository;
        this.emailSender = emailSender;
    }

    public RegistrationRequest save(RegistrationRequest request) {
        return repository.save(request);
    }

    public void notifyAdmins(Integer id) {
        List<Admin> admins = adminRepository.findAll();
        for(Admin admin : admins) {
            String title = "Registration request";
            String content = "Please review the following registration request:\n";
            content += "http://localhost:8000/#/reviewRequest/" + id.toString();
            emailSender.send("milica.popovic55@hotmail.com", title, content);
        }
    }
}
