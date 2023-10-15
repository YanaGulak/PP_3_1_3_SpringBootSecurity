package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;


@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/admin")
public class SecurityAdminController {

    private final UserService userService;
    private final RoleService roleService;
    private static final String REDIRECT = "redirect:/admin";

    //
    public SecurityAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    //выводим всех пользователей на view "admin"
    @GetMapping
    public String showUsers(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    //получаем форму для добавления нового пользователя
    @GetMapping("/registration")
    public String createForm(ModelMap model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll()); //чтобы в представлении поставить галочку у нужной роли
        return "/registration";
    }

    //создаем нового
    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") @Valid User user, ModelMap model) {
        model.addAttribute("roles", roleService.findAll());
        userService.saveUser(user);
        return REDIRECT;
    }

    //получаем форму на изменения
    @GetMapping("/edit/{id}")
    public String updateForm(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roles", roleService.findAll());
        return "edit";
    }

    // меняем данные пользователя
    @PutMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, @PathVariable("id") Long id,
                         BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            return "edit";
        } else {
            userService.updateUser(user,id);
        }
        return REDIRECT;
    }

    //удаляем пользователя
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return REDIRECT;
    }
}
