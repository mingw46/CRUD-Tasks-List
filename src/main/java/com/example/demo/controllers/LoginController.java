package com.example.demo.controllers;

import com.example.demo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String UserLoginForm(Model model){
        model.addAttribute("user", new User());
        return "loginForm";
    }
}
