package com.example.demo.model;

public class Admin extends User {
    private Boolean isMain;

    public Admin() {
        super();
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }
}
