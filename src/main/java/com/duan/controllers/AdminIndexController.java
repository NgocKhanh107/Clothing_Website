package com.duan.controllers;

import java.security.Principal;

import com.duan.repositories.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminIndexController {
	@Autowired
	userRepo dao;
	
	@GetMapping("/admin/index")
	public String index(Model model, Principal principal) {
		return "manager/index";
	}
}
