package com.mail.portal.controller;

import com.mail.portal.model.EmailTemplate;
import com.mail.portal.payloads.CommunicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

@CrossOrigin
@RestController
@EntityScan(value="com.mail.portal.model")
public class HandleFormSubmissionController {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("luthando9@gmail.com");
        //mailSender.setPassword("@Akhiwe123");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @RequestMapping(value="sendEmail", method = RequestMethod.POST)
    public ResponseEntity sendEmail(@RequestBody CommunicationDTO communicationDTO){
        String meesage = "";
        if(Objects.nonNull(communicationDTO)){

            //List<EmailTemplate> emailTemplates = emailTemplateRepository.findAll();

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(communicationDTO.getRecipientEmail());
            message.setSubject("Welcome to the Company");
            message.setText("wwwwwww");


            getJavaMailSender().send(message);

        }
        return ResponseEntity.ok("Sent");
    }
}
