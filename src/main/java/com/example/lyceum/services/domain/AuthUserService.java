package com.example.lyceum.services.domain;

import com.example.lyceum.configurations.CustomPasswordEncoderConfig;
import com.example.lyceum.exceptions.UserAlreadyExistsException;
import com.example.lyceum.models.dto.UserDto;
import com.example.lyceum.models.enums.Role;
import com.example.lyceum.models.jpa.domain.AuthUser;
import com.example.lyceum.repositories.domain.AuthUserRepository;
import com.example.lyceum.services.body.UserService;
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
    private final CustomPasswordEncoderConfig customPasswordEncoderConfig;
    private final UserService userService;

    @Autowired
    public AuthUserService(AuthUserRepository authUserRepository,
                           @Lazy CustomPasswordEncoderConfig customPasswordEncoderConfig, UserService userService) {
        this.authUserRepository = authUserRepository;
        this.customPasswordEncoderConfig = customPasswordEncoderConfig;
        this.userService = userService;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) {
        AuthUser authUser = authUserRepository.findByEmail(email);
        log.error("Пользователь: {} получен", authUser);
        return authUser;
    }

    @Transactional
    public void createAuthUser(UserDto userDto) {
        AuthUser authUser = new AuthUser();
        authUser.setEmail(userDto.getEmail());
        authUser.setPassword(customPasswordEncoderConfig.encode(userDto.getPassword()));
        authUser.setActive(true);
        authUser.setRoles(Collections.singleton(Role.USER));

        log.warn("Try to save user with EMAIL: {}", authUser.getEmail());

        try {
            authUserRepository.save(authUser);
        } catch (DataIntegrityViolationException ex) {
            throw new UserAlreadyExistsException("User with EMAIL: " + userDto.getEmail() +" already exists", ex);
        }
        log.warn("AuthUser with EMAIL: {} saved successfully", authUser.getEmail());

        userService.createProfUser(authUser);

    }

    @Transactional
    public AuthUser getAuthUserById(Long id){
        return authUserRepository.findAuthUserById(id);
    }
}