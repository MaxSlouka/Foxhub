package com.gfa.foxbook.foxbook.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {
private final JavaMailSender mailSender;

    public void send(String from, String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        mailSender.send(message);
    }

//    public void sendWithAttach(String from, String to, String subject,
//                               String text, String attachName,
//                               InputStreamSource inputStream) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(text, true);
//        helper.addAttachment(attachName, inputStream);
//        mailSender.send(message);
//    }




}
