package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Client;
import com.example.demo.model.ClientReview;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.ClientReviewRepository;
import com.example.demo.service.emailSenders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class ClientReviewService {
    private ClientReviewRepository repository;
    private AdminRepository adminRepository;
    private EmailSender emailSender;

    @Autowired
    public ClientReviewService(ClientReviewRepository repository, AdminRepository adminRepository, EmailSender emailSender) {
        this.repository = repository;
        this.adminRepository = adminRepository;
        this.emailSender = emailSender;
    }

    public ClientReview findOne(Integer id) {
        return repository.findById(id).orElseGet(null);
    }

    public ClientReview save(ClientReview clientReview) {
        clientReview = this.repository.save(clientReview);
        if (clientReview.getPenaltyRequested()) sendEmail(clientReview);
        return clientReview;
    }

    private void sendEmail(ClientReview review) {
        String title = "Client penalty request";
        String owner = String.format("%s %s (%s)", review.getIssuedBy().getName(), review.getIssuedBy().getSurname(), review.getIssuedBy().getEmail());
        String client = String.format("%s %s (%s)", review.getClient().getName(), review.getClient().getSurname(), review.getClient().getEmail());
        String content = String.format("Owner %s requested a penalty for client %s due to missing a reservation.\n" +
                "To review the request, please click the link below\nhttps://localhost:8000/#/viewPenaltyRequest/%d", owner, client, review.getId());
        List<Admin> admins = adminRepository.findAll();
        for (Admin admin : admins) {
            emailSender.send(admin.getEmail(), title, content);
        }
    }
}
