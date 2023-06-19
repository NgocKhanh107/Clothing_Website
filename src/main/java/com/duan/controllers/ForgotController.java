package com.duan.controllers;

import com.duan.dto.ForgotPassword;
import com.duan.entities.Users;
import com.duan.repositories.userRepo;
import com.duan.service.EmailSenderService;
import com.duan.service.MailerServiceImpl;


import com.duan.validator.ForgotPassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class ForgotController  {
    @Autowired
    ForgotPassValidator forgotPassValidator;
    @Autowired
    MailerServiceImpl service;
    @Autowired
    EmailSenderService serviceemail;
    @Autowired
    userRepo dao;
    @GetMapping("/forgot-password")
    public String index(Model model) {
        ForgotPassword entity = new ForgotPassword();
        model.addAttribute("forgotPassword", entity);
        return "user/forgot-password";
    }
    @PostMapping("/forgot-password")
    public String send(@ModelAttribute("forgotPassword") @Valid ForgotPassword entity, BindingResult result, Model model)  {
        model.addAttribute("check", false);
        if(!result.hasErrors()) {
            Users user = dao.findByEmail(entity.getEmail());
            //chưa bắt lỗi nếu không tìm thấy email
            serviceemail.sendEmail(entity.getEmail(),"Làm mới mật khẩu!","Vui lòng click vào link này: "+ "http://localhost:8080/reset-password/?code="+user.getPassword()+"&email="+user.getEmail() +" để reset mật khẩu.");
//            System.out.println(user.getPassword());
//            System.out.println(user.getEmail());
            return "user/success-forgot";
        }
        return "user/forgot-password";
    }
}
