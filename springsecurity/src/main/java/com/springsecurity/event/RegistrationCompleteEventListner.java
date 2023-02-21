package com.springsecurity.event;

import com.springsecurity.entity.User;
import com.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Slf4j
public class RegistrationCompleteEventListner implements
        ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the verification token for the user with link

        User user=event.getUser();
        String token= UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);

        //send mail to user

    String url= event.getApplicationUrl()+"/VerifyRegistration?token="+token;
    //send verification email

    log.info("Click the link to verify the Account: {}",url);
    }
}
