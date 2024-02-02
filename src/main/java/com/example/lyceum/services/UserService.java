package com.example.lyceum.services;

import com.example.lyceum.configurations.CustomPasswordEncoder;
import com.example.lyceum.exceptions.UserAlreadyExistsException;
import com.example.lyceum.models.dto.UserDto;
import com.example.lyceum.models.enums.Role;
import com.example.lyceum.models.jpa.User;
import com.example.lyceum.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;


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

    @Transactional
    public void createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(customPasswordEncoder.encode(userDto.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        log.warn("Try to save user with EMAIL: {}", user.getEmail());
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
             throw new UserAlreadyExistsException("User with EMAIL: " + userDto.getEmail() +" already exists", ex);
        }
        log.warn("User with EMAIL: {} saved successfully", user.getEmail());
    }
}
