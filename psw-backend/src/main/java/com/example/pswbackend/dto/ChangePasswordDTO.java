package com.example.pswbackend.dto;

public class ChangePasswordDTO {

    private Long id;
    private String newPassword;
    private String confirmPassword;

    public Long getId() {
        return id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
