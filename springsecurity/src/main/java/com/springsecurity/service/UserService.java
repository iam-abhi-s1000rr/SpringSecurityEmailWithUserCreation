package com.springsecurity.service;

import com.springsecurity.entity.User;
import com.springsecurity.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);
}
