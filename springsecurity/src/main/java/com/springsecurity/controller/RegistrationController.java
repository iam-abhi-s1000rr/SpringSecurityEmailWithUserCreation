package com.springsecurity.controller;

import com.springsecurity.entity.User;
import com.springsecurity.event.RegistrationCompleteEvent;
import com.springsecurity.model.UserModel;
import com.springsecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
//    @Autowired
//    private ApplicationEventPublisher publisher;
    @Autowired private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user=userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
        return "Success";
    }

//    @PostMapping("/register")
//    public String registerUser(@RequestBody UserModel userModel){
//        User user=userService.registerUser(userModel);
//        publisher.publishEvent(new RegistrationCompleteEvent(user,"url"));
//
//        return "success";
//    }


    private String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()
                        +":"
                        +request.getServerPort()
                        +request.getContextPath();
    }
    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result=userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("Valid")){
            return "User Verfied Successfully !";
        }
        return "Bad User";
    }
    @GetMapping("/hello")
    public String hello(){
        return "Namsakar Shet !!";
    }
}
