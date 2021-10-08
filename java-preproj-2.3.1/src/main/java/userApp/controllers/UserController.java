package userApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import userApp.entities.User;
import userApp.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping( "/list")
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
        User user = new User(login, name, lastname);
        userService.addUser(user);
        return "redirect:list";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/users/list";
    }

    @GetMapping( "/{id}")
    public String userInfo(
            Model model,
            @PathVariable("id") long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "notFoundPage";
        } else {
            model.addAttribute("user", user);
            return "userInfo";
        }
    }

    @PostMapping( "/{id}/put")
    public String userPatch(
            @ModelAttribute("login") String login,
            @ModelAttribute("name") String name,
            @ModelAttribute("lastName") String lastname,
            @PathVariable("id") long id) {
        User user = new User(login, name, lastname);
        userService.updateUser(id, user);
        return "redirect:/users/" + id;
    }
}
