package com.levi9.listeners;

import java.util.UUID;

import com.levi9.domain.User;
import com.levi9.listeners.events.OnRegistrationCompleteEvent;
import com.levi9.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String appUrl = event.getAppUrl();
        if (appUrl.contains("localhost")) {
            appUrl = appUrl + ":8080";
        }
        String confirmationUrl = appUrl + "/registration/confirm?token=" + token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("Follow the link " + "http://" + confirmationUrl);
        System.out.println("Email is ready to send");
        mailSender.send(email);
    }
}
