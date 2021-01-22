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
class EmailSenderService {

    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(SimpleMailMessage email) {
        try {
            javaMailSender.send(email);
        }catch (MailException e){
            System.out.println(e);
            throw new ResturaApiException("Exception occured when sending mail to " + Arrays.toString(email.getTo()));
        }
    }
}
