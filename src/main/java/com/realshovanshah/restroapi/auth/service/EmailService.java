package com.realshovanshah.restroapi.auth.service;

import com.realshovanshah.restroapi.utils.ResturaApiException;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
class EmailService {

    private JavaMailSender javaMailSender;

    void sendConfirmationMail(String userMail, String token, String prompt) {

        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Mail Confirmation Link!");
        mailMessage.setFrom("realshovanshah@gmail.com");
        mailMessage.setText(prompt + token);

        sendEmail(mailMessage);
    }

    @Async
    private void sendEmail(SimpleMailMessage email) {
        try {
            javaMailSender.send(email);
        }catch (MailException e){
            System.out.println(e);
            throw new ResturaApiException("Exception occured when sending mail to " + Arrays.toString(email.getTo()));
        }
    }
}
