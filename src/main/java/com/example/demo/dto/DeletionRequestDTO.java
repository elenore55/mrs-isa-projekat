package com.example.demo.dto;

import com.example.demo.model.User;
import com.example.demo.model.enums.AdminApprovalStatus;

import java.time.LocalDateTime;

public class DeletionRequestDTO {
    private String reason;
    private String id;

    public DeletionRequestDTO(){}
    public DeletionRequestDTO(String reason, String id)
    {
        this.reason = reason;
        this.id = id;
    }



    public String getReason() {
        return reason;
    }

    public String getId() {
        return id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setId(String id) {
        this.id = id;
    }
}
