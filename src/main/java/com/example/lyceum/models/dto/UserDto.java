package com.example.lyceum.models.dto;

import com.example.lyceum.models.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private Boolean active;
    private Set<Role> roles;
}
