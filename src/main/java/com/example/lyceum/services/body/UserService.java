package com.example.lyceum.services.body;

import com.example.lyceum.models.jpa.body.User;
import com.example.lyceum.models.jpa.domain.AuthUser;
import com.example.lyceum.repositories.body.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createProfUser(AuthUser authUser){
        User user = new User();
        user.setAuthUser(authUser);
        userRepository.save(user);
        log.warn("Saving user with id:{}", authUser.getId());
    }

    @Transactional
    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }
}
