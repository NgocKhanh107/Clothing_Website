package com.duan.controllers;

import com.duan.dto.Item;
import com.duan.entities.*;
import com.duan.repositories.*;
import com.duan.service.SessionService;
import com.duan.service.ShoppingCartServiceImpl;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class DetailController {
    @Autowired
    SessionService sessionService;

    @Autowired
    ProductSizeDao productsizeDao;

    @Autowired
    ProductColorDao productcolorDao;

    @Autowired
    ProductCateDao productcateDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    FavoriteDao favoriteDao;

    @Autowired
    ShoppingCartServiceImpl cart;

    @Autowired
    CategoryDao categoryDao;

    @RequestMapping("/shop/detail/{id}")
    public String index(@PathVariable("id") int id, Model model) {
        sessionService.set("cateId", id);
        /*
         * DetailModel entity = new DetailModel(); model.addAttribute("detail", entity);
         */

        List<ProductSize> list = productsizeDao.getSizeProduct(id);
        List<ProductColor> list2 = productcolorDao.getLoadImage(id);


        if(!list2.isEmpty()) {
            ProductColor c = (ProductColor) list2.get(0);
            model.addAttribute("checkColor", c.getColor().getId());
        }
        if(!list.isEmpty()) {
            model.addAttribute("checkSize", list.get(0).getSize().getId());
        }

        model.addAttribute("check", false);
        model.addAttribute("quality", 1);
        return "user/detail";
    }
    @PostMapping("/shop/detail/{id}")
    public String add(@PathVariable("id") int id, Model model, HttpServletRequest req) {
        String color = req.getParameter("color");
        String size = req.getParameter("size");
        String quality = req.getParameter("quality");
        Product product = productDao.getById(id);

        Item entity = new Item();
        entity.setId(id);

        if(color!=null) {
            entity.setIdColor(Integer.parseInt(color));
            req.setAttribute("checkColor", Integer.parseInt(color));
        }

        if(size!=null) {
            entity.setIdSize(Integer.parseInt(size));
            req.setAttribute("checkSize", Integer.parseInt(size));
        }

        entity.setImage(product.getImage());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setQuality(Integer.parseInt(quality));

        cart.add(id, entity);

        sessionService.set("countProduct", cart.getCount());

        req.setAttribute("quality", Integer.parseInt(quality));
        model.addAttribute("alert", "Thông báo!");
        model.addAttribute("content", "Sản phẩm đã được thêm vào giỏ hàng!");
        model.addAttribute("check", true);
        return "user/detail";
    }

//    @ModelAttribute("procate")
//    public List<ProductCate> procate(Model model) {
//        List<ProductCate> list = productcateDao.getSelectCategory();
//        return list;
//    }

    @ModelAttribute("loadMultiImage")
    public List<ProductColor> loadMultiImage(@PathVariable("id") int id, Model model){
        List<ProductColor> list = productcolorDao.getLoadImage(id);
        return list;
    }

    @ModelAttribute("loadInfo")
    public Product loadInfo(@PathVariable("id") int id, Model model){
        Product entity = productDao.getById(id);
        return entity;
    }

    @ModelAttribute("bonusProduct")
    public List<Product> bonusProduct(@PathVariable("id") int id){
        List<Product> list = productDao.fillBonusProduct(id);
        return list;
    }

    @ModelAttribute("loadMultiSize")
    public List<ProductSize> loadMultiSize(@PathVariable("id") int id){
        List<ProductSize> list = productsizeDao.getSizeProduct(id);
        return list;
    }
    @ModelAttribute("loadMultiColor")
    public List<ProductColor> loadMultiColor(@PathVariable("id") int id){
        List<ProductColor> list = productcolorDao.getColorProduct(id);
        return list;
    }
    @ModelAttribute("category")
    public List<Category> procate(Model model) {
        List<Category> list = categoryDao.findAll();
        System.out.println(list.size());
        return list;
    }
    @ModelAttribute("checkFavorite")
    public boolean checkFavorite(@PathVariable("id") int id) {
        boolean check = false;
        Users user = sessionService.get("userLogin");
        //Product product = productDao.getById(id);
        if(user != null) {
            Favorite entity = favoriteDao.getFavorite(user.getEmail(), id);
            if(entity == null) {
                check = true;
            }
        }
        return check;
    }
}
