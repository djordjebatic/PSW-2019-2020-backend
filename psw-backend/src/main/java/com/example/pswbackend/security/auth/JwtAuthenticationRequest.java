package com.example.pswbackend.security.auth;

public class JwtAuthenticationRequest {

    private String email;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String emailAddress, String password) {
        this.setEmail(emailAddress);
        this.setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String emailAddress) {
        this.email = emailAddress;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
