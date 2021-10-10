package com.example.kemen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.kemen.entities.User;
import com.example.kemen.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String userInfo(
            Model model,
            @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "userInfo";
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
        userService.updateUser(user.getId(), user);
        return "redirect:/user/info";
    }
}

