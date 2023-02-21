package com.springsecurity.service;

import com.springsecurity.config.WebSecurityConfig;
import com.springsecurity.entity.User;
import com.springsecurity.entity.VerificationToken;
import com.springsecurity.model.UserModel;
import com.springsecurity.repository.UserRepository;
import com.springsecurity.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
   private UserRepository userRepository;
    @Override
    public User registerUser(UserModel userModel) {
        User user=new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken=new VerificationToken(user,token);
        verificationTokenRepository.save(verificationToken);
    }

    

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken=verificationTokenRepository.findByToken(token);
        if(verificationToken==null){
            return "invalid";
        }

        // now we need to check the expiry time of the token for the current user
        User user=verificationToken.getUser();
        Calendar cal=Calendar.getInstance();

        // now checking the validation time & writing the code to check the expiry time
        if((verificationToken.getExpirationTime().getTime()
                -cal.getTime().getTime())<=0){
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }

        // setting the user Enabled/ Active or unactive
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }
}
