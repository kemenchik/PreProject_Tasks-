package userApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import userApp.entities.User;
import userApp.services.UserService;

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
        userService.updateUser(user.getId(), user);
        return "redirect:/user";
    }
}
