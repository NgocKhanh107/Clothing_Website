package com.duan.controllers;

import com.duan.dto.FillProColor;
import com.duan.dto.ProductColorModel;
import com.duan.entities.Color;
import com.duan.entities.Product;
import com.duan.repositories.ColorDao;
import com.duan.repositories.FillProColorDao;
import com.duan.repositories.ProductDao;
import com.duan.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin/productcolorForm/")
public class ProductColorController {
    @Autowired
    SessionService session;

    @Autowired
    ColorDao colorDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    FillProColorDao procolorDao;

    @GetMapping("/form")
    public String index(Model model, @RequestParam("keyword9") Optional<String> name,
                        @RequestParam("p") Optional<Integer> p2) {
        // Load select category
        Map<Integer, String> map = new HashMap<Integer, String>();
        List<Color> list = colorDao.findAll();
        for (Color c : list) {
            map.put(c.getId(), c.getName());
        }
        ProductColorModel entity = new ProductColorModel();
        model.addAttribute("productcolor", entity);
        model.addAttribute("color", map);

        // Load select product
        List<Product> list2 = productDao.findColor(list.get(0).getId());
        // List<Product> list = productDao.findProduct();
        Map<Integer, String> map2 = new HashMap<Integer, String>();
        for (Product p : list2) {
            map2.put(p.getId(), p.getName());
        }
        model.addAttribute("product", map2);

        // find by name
        String findName;
        if (session.get("keyword9") == null) {
            findName = name.orElse("");
        } else {
            findName = name.orElse(session.get("keyword9"));
        }

        session.set("keyword9", findName);

        Pageable pageable = PageRequest.of(p2.orElse(0), 5);

        Page<FillProColor> page = procolorDao.fillToTable("%" + findName + "%", pageable);
        model.addAttribute("procolorItem", page);

        return "manager/productcolorForm";
    }

}
