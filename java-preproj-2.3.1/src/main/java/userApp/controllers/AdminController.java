package userApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import userApp.entities.User;
import userApp.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @PostMapping("/create")
    public String createUser(
            @ModelAttribute("login") String login,
            @ModelAttribute("name") String name,
            @ModelAttribute("lastName") String lastname) {
        User user = new User(login, name, lastname, "123");
        userService.addUser(user);
        return "redirect:/admin/list";
    }

    @GetMapping("{id}")
    public String userInfo(
            Model model,
            @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "adminUserInfo";
    }

    @PostMapping("/put/{id}")
    public String userPatch(
            @ModelAttribute("login") String login,
            @ModelAttribute("name") String name,
            @ModelAttribute("lastName") String lastname,
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
