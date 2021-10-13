package com.example.kemen.controllers;

import com.example.kemen.entities.User;
import com.example.kemen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public User userInfo(
            Model model,
            @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return user;
    }

    @PostMapping("/put")
    public String userPatch(
            @ModelAttribute("login") String login,
            @ModelAttribute("name") String name,
            @ModelAttribute("lastName") String lastname,
            @AuthenticationPrincipal User user) {
        if (login != null) {
            user.setLogin(login);
        }
        if (name != null) {
            user.setName(name);
        }
        if (lastname != null) {
            user.setLastName(lastname);
        }
        userService.updateUser(user);
        return "redirect:/user/info";
    }
}

