package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Complaint;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.RegistrationRequestRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public RegistrationRequest save(RegistrationRequest request) {
        return repository.save(request);
    }

    public void notifyAdmins() {
        List<Admin> admins = adminRepository.findAll();
        for(Admin admin : admins) {
            String title = "Registration request";
            String content = "There is a new registration request\nPlease review it!";
            emailSender.send(admin.getEmail(), title, content);
        }
    }
    public List<RegistrationRequest> findAll() {
        return this.repository.findAll();
    }

    public RegistrationRequest findOne(Integer id){return this.repository.findById(id).orElse(null);}
    public RegistrationRequest update(RegistrationRequest request){return this.repository.save(request);}
    public void updateStatus(Integer id, AdminApprovalStatus status){this.repository.updateStatus(id,status);}


    public void remove(Integer id) { repository.deleteById(id);}

}
