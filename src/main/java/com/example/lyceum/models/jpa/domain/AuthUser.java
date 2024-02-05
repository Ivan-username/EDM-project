package com.example.lyceum.models.jpa.domain;


import com.example.lyceum.models.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "AUTH_USERS")
public class AuthUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD", length = 1000)
    private String password;

    @Column(name = "ACTIVE")
    private Boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID")
    )
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

//todo      ----------security----------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AuthUser;
    }
}
