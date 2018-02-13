package com.example.demo.services;

public interface EmailService {
    void SendEmail(String to, String subject, String content);
}
