package com.springsecurity.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel {
    private String firstName;
    private String lastName;

    private String email;
    private String password;
    private String matchingPassword;
}
