package com.example.pswbackend.services;

public interface EmailService {
    void sendEmail(String recipient, String subject, String message);
}
