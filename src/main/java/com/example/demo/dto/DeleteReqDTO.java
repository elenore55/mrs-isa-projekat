package com.example.demo.dto;

import com.example.demo.model.DeletionRequest;
import com.example.demo.model.User;
import com.example.demo.model.enums.AdminApprovalStatus;

public class DeleteReqDTO {
    private int id;
    private AdminApprovalStatus status;
    private String reason;
    private UserDTO userDTO;

    public DeleteReqDTO() {
    }

    public DeleteReqDTO(int id, AdminApprovalStatus status, UserDTO userDTO) {
        this.id = id;
        this.status = status;
        this.userDTO = userDTO;
    }

    public DeleteReqDTO(DeletionRequest deletionRequest){
        this.id = deletionRequest.getId();
        this.status = deletionRequest.getStatus();
        this.userDTO = new UserDTO(deletionRequest.getSentBy());
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public AdminApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(AdminApprovalStatus status) {
        this.status = status;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
