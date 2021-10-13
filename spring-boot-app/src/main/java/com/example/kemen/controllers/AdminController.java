package com.example.kemen.controllers;

import com.example.kemen.entities.Role;
import com.example.kemen.entities.User;
import com.example.kemen.repos.RoleCrud;
import com.example.kemen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleCrud roleCrud;


    @PostMapping("/create")
    public void createUser(String login, String name, String lastname, String password) {
        User user = new User(login, name, lastname, password, roleCrud.getAuthorityByRole(Role.ROLE_USER));
        userService.addUser(user);
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User userInfo(
            Model model,
            @PathVariable("id") long id) {
        model.addAttribute("isAdmin", userService.getUserById(id).isAdmin());
        model.addAttribute("user", userService.getUserById(id));
        return userService.getUserById(id);
    }

    @PostMapping("/put/{id}")
    public void userPatch(String login, String name, String lastname, String password, boolean isAdmin, @PathVariable("id") long id) {
        User user = userService.getUserById(id);
        if (login != null) {
            user.setLogin(login);
        }
        if (password != null) {
            user.setPassword(password);
        }
        if (name != null) {
            user.setName(name);
        }
        if (lastname != null) {
            user.setLastName(lastname);
        }
        if (isAdmin) {
            if (!user.isAdmin()) {
                user.addAuthority(roleCrud.getAuthorityByRole(Role.ROLE_ADMIN));
            }
        } else {
            if (user.isAdmin()) {
                user.removeAuthority(roleCrud.getAuthorityByRole(Role.ROLE_ADMIN));
            }
        }
        userService.updateUser(user);
    }

    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
    }
}

