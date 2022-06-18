package com.example.demo.dto;

import com.example.demo.model.enums.AdminApprovalStatus;

public class DeleteReqDTO {
    private int id;
    private AdminApprovalStatus status;

    public DeleteReqDTO() {
    }

    public DeleteReqDTO(int id, AdminApprovalStatus status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public UserDTO getReqBy() {
//        return reqBy;
//    }
//
//    public void setReqBy(UserDTO reqBy) {
//        this.reqBy = reqBy;
//    }

    public AdminApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(AdminApprovalStatus status) {
        this.status = status;
    }
}
