package com.deepanshu.dairydriveapi.services.impl;

import com.deepanshu.dairydriveapi.configs.AppConfigurations;
import com.deepanshu.dairydriveapi.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private AppConfigurations appConfig;

    @Value("${spring.mail.username}")
    private String apiEmailAddress;

    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(this.apiEmailAddress);

        emailSender.send(message);
    }

    @Override
    @Async
    public void sendEmailWithHtmlContent(String to, String subject, String htmlBody) {


        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(htmlBody,true);
            messageHelper.setFrom(this.apiEmailAddress);

            emailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            LoggerFactory.getLogger(this.getClass().getName()).error(e.getMessage());
        }
    }
}
