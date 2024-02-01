package com.example.lyceum.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/lyceum-edm")
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model){
        return "profile";
    }
}
