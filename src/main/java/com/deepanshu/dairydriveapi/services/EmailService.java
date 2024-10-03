package com.deepanshu.dairydriveapi.services;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
    void sendEmailWithHtmlContent(String to, String subject, String htmlBody);
}
