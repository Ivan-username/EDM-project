package com.example.lyceum.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VerificationController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
