package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void SendEmail(String to, String subject, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo("pawelstrupiechowski@springboot.pl");
           helper.setFrom("pawelstrupiechowski@springboot.pl");
            helper.setSubject(subject);
            helper.setText(content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }
}
