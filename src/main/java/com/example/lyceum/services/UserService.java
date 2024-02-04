package com.example.lyceum.services;

import com.example.lyceum.models.dto.UserDto;
import com.example.lyceum.models.jpa.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService {


    @Transactional
    public void addDocumentsToUser() {
        User user = new User();
    }
}