package com.example.lyceum.services;

import com.example.lyceum.configurations.CustomPasswordEncoder;

import com.example.lyceum.models.jpa.User;

import com.example.lyceum.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy CustomPasswordEncoder customPasswordEncoder) {
        this.userRepository = userRepository;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        log.error("Пользователь с почтой: {} получен", user);
        return user;
    }
}
