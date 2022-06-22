package com.example.demo.dto;

import com.example.demo.model.Complaint;
import com.example.demo.model.enums.AdminApprovalStatus;

import java.time.LocalDateTime;

public class ComplaintAdminDTO {
    private String content;
    private ClientDTO clientDTO;
    private Integer id;
    private AdminApprovalStatus adminApprovalStatus;
    private LocalDateTime dateTime;

    public ComplaintAdminDTO(){}

    public ComplaintAdminDTO(String content, ClientDTO clientDTO, Integer id, AdminApprovalStatus adminApprovalStatus, LocalDateTime dateTime) {
        this.content = content;
        this.clientDTO = clientDTO;
        this.id = id;
        this.adminApprovalStatus = adminApprovalStatus;
        this.dateTime = dateTime;
    }

    public ComplaintAdminDTO(Complaint complaint) {
        this.content = complaint.getContent();
        this.clientDTO = new ClientDTO(complaint.getIssuedBy());
        this.id = complaint.getId();
        this.adminApprovalStatus = complaint.getStatus();
        this.dateTime = complaint.getDateTime();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    @Override
    public String toString() {
        return "ComplaintAdminDTO{" +
                "content='" + content + '\'' +
                ", clientDTO=" + clientDTO +
                ", id=" + id +
                ", adminApprovalStatus=" + adminApprovalStatus +
                ", dateTime=" + dateTime +
                '}';
    }
}

