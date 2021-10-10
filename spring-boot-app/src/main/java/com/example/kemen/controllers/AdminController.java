package com.example.kemen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.kemen.entities.Role;
import com.example.kemen.entities.User;
import com.example.kemen.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public String createUser(
            @ModelAttribute("login") String login,
            @ModelAttribute("name") String name,
            @ModelAttribute("lastName") String lastname) {
        User user = new User(login, name, lastname, "123");
        userService.addUser(user);
        return "redirect:/admin/list";
    }

    @GetMapping("/list")
    public String getUsers(Model model, @AuthenticationPrincipal User user) {
        List<User> users = userService.getAllUsers();
        boolean isAdmin = AuthorityUtils
                .authorityListToSet(user.getAuthorities())
                .contains(Role.ROLE_ADMIN.name());
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("{id}")
    public String userInfo(
            Model model,
            @PathVariable("id") long id) {
        boolean isAdmin = AuthorityUtils
                .authorityListToSet(userService.getUserById(id).getAuthorities())
                .contains(Role.ROLE_ADMIN.name());
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("user", userService.getUserById(id));
        return "adminUserInfo";
    }

    @PostMapping("/put/{id}")
    public String userPatch(
            @ModelAttribute("login") String login,
            @ModelAttribute("name") String name,
            @ModelAttribute("lastName") String lastname,
            @RequestParam(value = "isAdmin", required = false) boolean isAdmin,
            @PathVariable("id") long id) {
        User user = userService.getUserById(id);
        if (login != null) {
            user.setLogin(login);
        }
        if (name != null) {
            user.setName(name);
        }
        if (lastname != null) {
            user.setLastName(lastname);
        }
        if (isAdmin) {
            if (!AuthorityUtils.authorityListToSet(user.getAuthorities()).contains(Role.ROLE_ADMIN.name())) {
                userService.addAdmin(user);
            }
        } else {
            if (AuthorityUtils.authorityListToSet(user.getAuthorities()).contains(Role.ROLE_ADMIN.name())) {
                userService.removeAdmin(user);
            }
        }
        userService.updateUser(id, user);
        return "redirect:/admin/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/list";
    }

    @PostMapping("/add/{id}")
    public String addAdminRole(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        userService.addAdmin(user);
        return "redirect:/admin/list";
    }
}

