package pbo.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import pbo.springboot.project.model.User;

import pbo.springboot.project.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){

        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute("user", new User());

        return "auth/register";
    }

    @PostMapping("/register/save")
    public String save(@ModelAttribute User user){

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        repository.save(user);

        return "redirect:/login";
    }
}