package com.example.demo.dto;

public class ChangePasswordDTO {
    private String old;
    private String newPass;
    private String id;

    public ChangePasswordDTO() {}

    public ChangePasswordDTO(String old, String newPass, String id) {
        this.old = old;
        this.newPass = newPass;
        this.id = id;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
