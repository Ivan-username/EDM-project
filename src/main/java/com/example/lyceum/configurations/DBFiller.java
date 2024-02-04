package com.example.lyceum.configurations;

import com.example.lyceum.models.domain.AuthUser;
import com.example.lyceum.models.enums.Role;
import com.example.lyceum.models.jpa.User;
import com.example.lyceum.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

// to delete
@Slf4j
@Component
@AllArgsConstructor
public class DBFiller {

    private EntityManagerFactory entityManagerFactory;


    public void fillDb() {

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        AuthUser authUser = new AuthUser();
        authUser.setEmail("user@ya.ru");
        authUser.setActive(true);
        authUser.setPassword("$2a$08$Qe5RFoouKEzweUDP6hkYueYmA/8YCrL8/1AZgQL3qnP6Esc1HtK0O");
        authUser.setRoles(Set.of(Role.USER));
        em.persist(authUser);
        em.getTransaction().commit();
    }

    @EventListener
    public void onStart(ContextRefreshedEvent event) {

//        boolean flag = true; //заполнить
        boolean flag = false; //не заполнить

        if (flag){
            log.warn("Заполняем базу");
            fillDb();
        } else log.warn("Не заполняем базу");
    }
}
