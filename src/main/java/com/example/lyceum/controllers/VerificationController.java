package com.example.lyceum.controllers;


import com.example.lyceum.exceptions.UserAlreadyExistsException;
import com.example.lyceum.models.domain.AuthUser;
import com.example.lyceum.models.dto.UserDto;
import com.example.lyceum.services.domain.AuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class VerificationController {

    private final AuthUserService authUserService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(UserDto userDto, Model model) {
        try {
            authUserService.createUser(userDto);
        } catch (UserAlreadyExistsException ex) {
            model.addAttribute("param", true);
            log.error(ex.getMessage());
            return "redirect:/registration";
        } catch (Exception ex){
            log.error("Unexpected Exception while user creating: ", ex);
        }
        return login();
    }
}
