package com.duan.controllers;

import com.duan.dto.ShowSize;
import com.duan.entities.*;
import com.duan.repositories.CategoryDao;
import com.duan.repositories.ProductColorDao;
import com.duan.repositories.ProductDao;
import com.duan.repositories.ProductSizeDao;
import com.duan.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ShopController {
    @Autowired
    SessionService sessionService;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductSizeDao productSizeDao;

    @Autowired
    ProductColorDao productColorDao;
    @GetMapping("/shop/category/{id}")
    public String index(@PathVariable("id") int id, Model model, @RequestParam("p") Optional<Integer> p) {
        sessionService.set("cateId", id);
        Pageable pageable = PageRequest.of(p.orElse(0), 9);

        Page<Product> list = productDao.fillShopDESC(id, pageable);
        model.addAttribute("shop", list);
        return "user/shop";
    }
    @ModelAttribute("nameCate")
    public String nameCate(@PathVariable("id") int id) {
        Category entity = categoryDao.getById(id);
        return entity.getName();
    }
    @ModelAttribute("color")
    public List<ProductColor> color(@PathVariable("id") int id) {
        List<ProductColor> list = productColorDao.getColor(id);
        return list;
    }

    @ModelAttribute("size")
    public List<ProductSize> size(@PathVariable("id") int id) {
        List<ProductSize> list = productSizeDao.getSize(id);
        return list;
    }
    @ModelAttribute("category")
    public List<Category> procate(Model model) {
        List<Category> list = categoryDao.findAll();
        System.out.println(list.size());
        return list;
    }
}
