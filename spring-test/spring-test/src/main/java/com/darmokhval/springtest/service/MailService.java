//package com.darmokhval.springtest.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MailService {
//    private final MailSender mailSender;
//    @Value("${spring.mail.username}")
//    private String mailFrom;
//
//    public MailService(MailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    public void sendEmail(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject(subject);
//        message.setText(text);
//        message.setTo(to);
//        message.setFrom(mailFrom);
//        mailSender.send(message);
//    }
//}
