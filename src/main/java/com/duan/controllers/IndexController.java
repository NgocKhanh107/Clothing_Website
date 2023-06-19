package com.duan.controllers;

import com.duan.dto.ShowCategory;
import com.duan.entities.*;
import com.duan.repositories.*;
import com.duan.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.userdetails.User;
import java.security.Principal;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    SessionService sessionService;
    @Autowired
    ManufactureDao manuDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryDao categorydao;
    @Autowired
    ProductCateDao productcateDao;
    @Autowired
    userRepo dao;
    @Autowired
    FavoriteDao favoriteDao;

    @GetMapping("/index")
    public String index(Model model, Principal principal) {
//            System.out.println("day la ......."+productDao.fillViewsDESC().size());
        return "user/index";
    }
    @GetMapping("/successLogin")
    public String login(Model model, Principal principal) {
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();d
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        System.out.println(loginedUser);
       Users entity = dao.findByEmail(loginedUser.getUsername());

        sessionService.set("userLogin", entity);
        int count = favoriteDao.countFavorite(entity.getId());
        sessionService.set("countFavorite", count);

        return "user/index";
    }

    @GetMapping("/logout")
    public String logout() {
        return "user/index";
    }

    @ModelAttribute("manufacture")
    public List<Manufacture> manufacture(Model model) {
        List<Manufacture> list = manuDao.findAll();
        return list;
    }
    @ModelAttribute("dates")
    public List<Product> fillDate(Model model) {
        List<Product> list = productDao.fillDateDESC();
        return list;
    }
    @ModelAttribute("views")
    public List<Product> fillViews(Model model) {
        List<Product> list = productDao.fillViewsDESC();
        return list;
    }

    @ModelAttribute("category")
    public List<Category> procate(Model model) {
        List<Category> list = categorydao.findAll();
        System.out.println(list.size());
        return list;
    }
    @ModelAttribute("count")
    public List<ProductCate> category(Model model) {
        List<ProductCate> list = productcateDao.getSelectCategory();
        return list;
    }
    @ModelAttribute("procate")
    public List<ProductCate> danhmuc(Model model) {
        List<ProductCate> list = productcateDao.getSelectCategory();
        return list;
    }
}
