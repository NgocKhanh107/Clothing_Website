package com.duan.controllers;

import com.duan.dto.UserLogin;
import com.duan.entities.Users;
import com.duan.repositories.userRepo;
import com.duan.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller

public class UserLoginController {
    @Autowired
    private userRepo dao;
    @Autowired
    private SessionService sessionService;
    @GetMapping("/login")
    public String index(Model model) {
        sessionService.set("code", "Asdsfdsf2=-##2");
        UserLogin entity = new UserLogin();
        model.addAttribute("userLogin", entity);
        return "user/login";
    }
    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("userLogin") @Valid UserLogin entity, BindingResult result) {
        if(result.hasErrors()) {
            return "user/login";
        }

        Users user = dao.findByEmail(entity.getUsername());
        sessionService.set("userLogin", user);
        System.out.println("đã đăng nhập");
        return "user/index";
    }

}
