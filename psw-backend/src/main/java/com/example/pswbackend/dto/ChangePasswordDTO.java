package com.example.pswbackend.dto;

public class ChangePasswordDTO {

    private String newPassword;
    private String oldPassword;

    public ChangePasswordDTO() {

    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}
