package com.duan.controllers;

import com.duan.dto.FillProSize;
import com.duan.dto.ProductSizeModel;
import com.duan.entities.Product;
import com.duan.entities.Size;
import com.duan.repositories.FillProSizeDao;
import com.duan.repositories.ProductDao;
import com.duan.repositories.ProductSizeDao;
import com.duan.repositories.SizeDao;
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
@RequestMapping("/admin/productsizeForm/")
public class ProductSizeController {
    @Autowired
    ProductSizeDao dao;
    @Autowired
    SessionService session;
    @Autowired
    SizeDao sizeDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    FillProSizeDao prosizeDao;

    @GetMapping("form")
    public String index(Model model, @RequestParam("keyword8") Optional<String> name,
                        @RequestParam("p") Optional<Integer> p2) {
        // Load select category
        Map<Integer, String> map = new HashMap<Integer, String>();
        List<Size> list = sizeDao.findAll();
        for (Size c : list) {
            map.put(c.getId(), c.getName());
        }
        ProductSizeModel entity = new ProductSizeModel();
        model.addAttribute("productsize", entity);
        model.addAttribute("size", map);

        // Load select product
        List<Product> list2 = productDao.findSize(list.get(0).getId());
        // List<Product> list = productDao.findProduct();
        Map<Integer, String> map2 = new HashMap<Integer, String>();
        for (Product p : list2) {
            map2.put(p.getId(), p.getName());
        }
        model.addAttribute("product", map2);

        // find by name
        String findName;
        if (session.get("keyword8") == null) {
            findName = name.orElse("");
        } else {
            findName = name.orElse(session.get("keyword8"));
        }

        session.set("keyword8", findName);

        Pageable pageable = PageRequest.of(p2.orElse(0), 5);

        Page<FillProSize> page = prosizeDao.fillToTable("%" + findName + "%", pageable);
        model.addAttribute("prosizeItem", page);

        return "manager/productsizeForm";
    }
}
