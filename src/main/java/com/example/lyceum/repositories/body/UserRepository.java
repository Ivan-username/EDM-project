package com.example.lyceum.repositories.body;

import com.example.lyceum.models.jpa.body.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long Id);
}
