package com.duan.controllers;

import com.duan.dto.UserRegister;
import com.duan.entities.Role;
import com.duan.entities.UserRole;
import com.duan.entities.Users;
import com.duan.repositories.RoleDao;
import com.duan.repositories.UserRoleDao;
import com.duan.repositories.userRepo;
import com.duan.service.EmailSenderService;
import com.duan.service.MailerServiceImpl;
import com.duan.service.SessionService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class UserRegisterController {
    @Autowired
    private userRepo dao;

    @Autowired
    MailerServiceImpl service;

    @Autowired
    EmailSenderService serviceemail;

    @Autowired
    SessionService sessionService;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String index(Model model) {
        UserRegister entity = new UserRegister();
        System.out.println("this is code: " + sessionService.get("code"));
        model.addAttribute("userRegister", entity);
        return "user/register";
    }
    @PostMapping("/register/{check}")
    public String save( @ModelAttribute("userRegister") @Valid UserRegister entity,
                       BindingResult result, @PathVariable("check") String check) {
        // System.out.println(check);

        if (check.equals("mailSender")) {
            int code = (int) Math.floor(((Math.random() * 899999) + 100000));
            sessionService.set("code", code);
            serviceemail.sendEmail(entity.getEmail(),"Xác nhận Email!", "Code xác nhận của bạn là: " + code);
//            service.queue(entity.getEmail(), "Xác nhận Email!", "Code xác nhận của bạn là: " + code);
        }

        if (result.hasErrors()) {
            //return "user/register";
        } else {
            // System.out.println("this is code: "+code);
            if (sessionService.get("code") == null) {
                result.rejectValue("code", "NotDuplicate.userRegister.code");
            } else {
                if (entity.getCode().equals(sessionService.get("code").toString()) == false) {
                    System.out.println("your code is " + entity.getCode());
                    System.out.println("this is code: " + sessionService.get("code"));
//                    result.rejectValue("code", "NotDuplicate.userRegister.code");
                } else {
                    System.out.println("đúng");
                    String password = passwordEncoder.encode(entity.getPassword());
                    Users user = new Users(entity.getEmail(), password, entity.getFullname());
                    dao.save(user);
                    Role role = roleDao.getOne(2);
                    UserRole userRole = new UserRole(user, role);
                    userRoleDao.save(userRole);
                    return "redirect:/login";
                }
            }
        }

        return "user/register";
    }
}
