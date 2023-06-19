package com.duan.controllers;

import com.duan.dto.ResetPassword;
import com.duan.entities.Users;
import com.duan.repositories.userRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class ResetPassController {
    @Autowired
    private com.duan.repositories.userRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/reset-password")
    public String index(Model model) {
        ResetPassword entity = new ResetPassword();
        model.addAttribute("resetPassword", entity);
        return "user/reset-password";
    }
    @PostMapping("/reset-password")
    public String save(@RequestParam(value = "code", required = true) String code,
                       @RequestParam(value = "email", required = true) String email,
                       @ModelAttribute("resetPassword") @Valid ResetPassword entity, BindingResult result) {

        if(result.hasErrors()) {
            return "user/reset-password";
        }

        else {
            Users user = userRepo.findByEmail(email);
            if(user == null) {
                return "redirect:/404page";
            }
            else {
                //System.out.println(code);
                //System.out.println(user.getPassword());
                if(code.equals(user.getPassword())) {
                    user.setPassword(entity.getPassword());
                    userRepo.save(user);
                    return "user/success-reset";
                }
                else {
                    return "redirect:/404page";
                }
            }
        }

        //return "user/success-reset";
    }
}
