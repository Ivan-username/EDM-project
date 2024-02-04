package com.example.lyceum.services.domain;

import com.example.lyceum.configurations.CustomPasswordEncoder;
import com.example.lyceum.exceptions.UserAlreadyExistsException;
import com.example.lyceum.models.domain.AuthUser;
import com.example.lyceum.models.dto.UserDto;
import com.example.lyceum.models.enums.Role;
import com.example.lyceum.repositories.UserRepository;
import com.example.lyceum.repositories.domain.AuthUserRepository;
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
public class AuthUserService implements UserDetailsService {


    private final AuthUserRepository authUserRepository;
    private final CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    public AuthUserService(AuthUserRepository authUserRepository,
                       @Lazy CustomPasswordEncoder customPasswordEncoder) {
        this.authUserRepository = authUserRepository;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) {
        AuthUser authUser = authUserRepository.findByEmail(email);
        log.error("Пользователь: {} получен", authUser);
        return authUser;
    }

    @Transactional
    public void createUser(UserDto userDto) {
        AuthUser authUser = new AuthUser();
        authUser.setEmail(userDto.getEmail());
        authUser.setPassword(customPasswordEncoder.encode(userDto.getPassword()));
        authUser.setActive(true);
        authUser.setRoles(Collections.singleton(Role.USER));

        log.warn("Try to save user with EMAIL: {}", authUser.getEmail());

        try {
            authUserRepository.save(authUser);
        } catch (DataIntegrityViolationException ex) {
            throw new UserAlreadyExistsException("User with EMAIL: " + userDto.getEmail() +" already exists", ex);
        }

        log.warn("User with EMAIL: {} saved successfully", authUser.getEmail());
    }
}