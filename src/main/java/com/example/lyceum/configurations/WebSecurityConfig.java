package com.example.lyceum.configurations;


import com.example.lyceum.models.enums.Role;
import com.example.lyceum.services.UserService;
import com.example.lyceum.services.domain.AuthUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//    @Autowired
    private final AuthUserService authUserService;

    public WebSecurityConfig(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    public PasswordEncoder customPasswordEncoder() {
        return new CustomPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/registration").permitAll()
                        .requestMatchers("/project/lyceum-edm/guest-page/**").hasAnyRole(String.valueOf(Role.USER), String.valueOf(Role.ADMIN)) // то что можно
                        .anyRequest().authenticated() // то что незя :)
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authUserService);
        authenticationProvider.setPasswordEncoder(customPasswordEncoder());
        return authenticationProvider;
    }
}
