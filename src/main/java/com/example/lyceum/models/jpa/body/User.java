package com.example.lyceum.models.jpa.body;

import com.example.lyceum.models.jpa.domain.AuthUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PROF_USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE_OF_CREATED")
    private LocalDateTime dateOfCreated;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTH_USER_ID")
    private AuthUser authUser;

    //todo documents dependency

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }
}

