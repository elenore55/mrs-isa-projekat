package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.DeletionRequest;
import com.example.demo.model.User;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.DeletionRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeletionRequestService {
    private final DeletionRequestRepository deletionRequestRepository;
    private final UserRepository userRepository;
    private AdminRepository adminRepository;
    private EmailSender emailSender;

    @Autowired
    public DeletionRequestService(DeletionRequestRepository deletionRequestRepository, UserRepository userRepository, AdminRepository adminRepository, EmailSender emailSender) {
        this.deletionRequestRepository = deletionRequestRepository;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.emailSender = emailSender;
    }

    public DeletionRequest save(DeletionRequest deletionRequest) {
        DeletionRequest request = deletionRequestRepository.save(deletionRequest);
        notifyAdmins(request.getId());
        return request;
    }

    public User findById(String id) {
        int n = Integer.parseInt(id);
        return userRepository.getById(n);
    }

    private void notifyAdmins(Integer id) {
        List<Admin> admins = adminRepository.findAll();
        for (Admin admin : admins) {
            String title = "Profile deletion request";
            String content = "Please review the following profile deletion request:\n";
            content += "http://localhost:8000/#/reviewProfileDeletionRequest/" + id.toString();
            emailSender.send(admin.getEmail(), title, content);
        }
    }
}
