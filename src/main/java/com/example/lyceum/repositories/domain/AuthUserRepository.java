package com.example.lyceum.repositories.domain;

import com.example.lyceum.models.jpa.domain.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository  extends JpaRepository<AuthUser, Long>{
        AuthUser findByEmail(String email);
        AuthUser findAuthUserById(Long id);
}
