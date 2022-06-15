package com.example.demo.dto;

import com.example.demo.model.Complaint;
import com.example.demo.model.enums.AdminApprovalStatus;

public class ComplaintAdminDTO {
    private String content;
    private ClientDTO clientDTO;
    private Integer id;
    private AdminApprovalStatus adminApprovalStatus;
    private

    public ComplaintAdminDTO(String content, ClientDTO clientDTO, Integer id, AdminApprovalStatus adminApprovalStatus) {
        this.content = content;
        this.clientDTO = clientDTO;
        this.id = id;
        this.adminApprovalStatus = adminApprovalStatus;
    }

    public ComplaintAdminDTO(Complaint complaint) {
        this.content = complaint.getContent();
        this.clientDTO = new ClientDTO(complaint.getIssuedBy());
        this.id = complaint.getId();
        this.adminApprovalStatus = complaint.getStatus();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AdminApprovalStatus getAdminApprovalStatus() {
        return adminApprovalStatus;
    }

    public void setAdminApprovalStatus(AdminApprovalStatus adminApprovalStatus) {
        this.adminApprovalStatus = adminApprovalStatus;
    }
}

