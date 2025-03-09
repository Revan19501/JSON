package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class UserController {
    @GetMapping("/user")
    public String showUserPage() {
        return "redirect:/user";
    }
}
