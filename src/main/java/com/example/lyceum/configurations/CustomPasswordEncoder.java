package com.example.lyceum.configurations;


import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword.toString(), BCrypt.gensalt(8));
    }

    @Override
    public boolean matches(CharSequence plainTextPassword, String passwordInDatabase) {
        return BCrypt.checkpw(plainTextPassword.toString(), passwordInDatabase);
    }
}