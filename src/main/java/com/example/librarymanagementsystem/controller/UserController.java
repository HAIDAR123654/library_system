package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "registerpage";
    }
    @PostMapping("/register")
    public String saveUser(User user){
        userService.createUser(user);
        return "redirect:/";
    }
}
