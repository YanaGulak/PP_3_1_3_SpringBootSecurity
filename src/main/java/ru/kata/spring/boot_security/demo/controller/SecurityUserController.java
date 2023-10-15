package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;

@Controller
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class SecurityUserController {

    private final UserDetailsService userService;

    public SecurityUserController(UserDetailsService userService) {
        this.userService = userService;
    }

    //логика отображения персональной страницы пользователя
    @GetMapping("/user")
    public String showUserInfo(Principal principal, ModelMap model) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("helloMessage", "Hello, " + user.getName() + " " + user.getLastName() + "!");
        return "user";
    }
}
