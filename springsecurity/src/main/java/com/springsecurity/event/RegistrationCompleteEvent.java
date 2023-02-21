package com.springsecurity.event;

import com.springsecurity.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;


@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;

    public RegistrationCompleteEvent(User user,String applicationUrl) {
        super(user);
        this.user=user;
        this.applicationUrl=applicationUrl;
    }
//    private User user;
//    private String applicationUrl;
//    public RegistrationCompleteEvent(User user, String applicationUrl){
//        super(user);
//        this.user=user;
//        this.applicationUrl=applicationUrl;
//    }


}
