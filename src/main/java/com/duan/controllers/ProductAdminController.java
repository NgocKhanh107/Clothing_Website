package com.duan.controllers;

import com.duan.dto.ProductModel;
import com.duan.entities.Manufacture;
import com.duan.entities.Product;
import com.duan.mapper.productmapper;
import com.duan.repositories.ManufactureDao;
import com.duan.repositories.ProductDao;
import com.duan.service.ParamService;
import com.duan.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin/productForm/")
public class ProductAdminController {
    @Autowired
    SessionService session;

    @Autowired
    ProductDao productDao;

    @Autowired
    ManufactureDao manuDao;

    @Autowired
    private productmapper mapper;

    @Autowired
    ParamService param;

    @GetMapping("list")
    public String index(Model model, @RequestParam("keyword5") Optional<String> name,
                        @RequestParam("p") Optional<Integer> p) {
        String findName;
        if (session.get("keyword5") == null) {
            findName = name.orElse("");
        } else {
            findName = name.orElse(session.get("keyword5"));
        }

        session.set("keyword5", findName);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);

        Page<Product> page = productDao.fillToTable("%" + findName + "%", pageable);
        model.addAttribute("productItem", page);

        return "manager/productTable";
    }

    @GetMapping("form")
    public String index(Model model) {
        // session.set("nameImage3", "");
        ProductModel entity = new ProductModel();
        model.addAttribute("productForm", entity);
        return "manager/productForm";
    }

    @GetMapping("list/delete/{id}")
    public String delete(Model model, @PathVariable("id") int id) {
        Product entity = productDao.getById(id);
        productDao.delete(entity);
        return "redirect:/admin/productForm/list";
    }

    @GetMapping("refresh")
    public String refresh() {
        return "redirect:/admin/productForm/form";
    }

    @PostMapping("form")
    public String save(Model model, @ModelAttribute("productForm") @Valid ProductModel entity,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "manager/productForm";
        }

        else {
            Manufacture manufacture = manuDao.getById(Integer.parseInt(entity.getManufacture()));

            String dateStr = entity.getManuDay();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateStr = sdf.format(date);

            Product product = new Product(entity.getName(), entity.getPrice(), param.save(entity.getImage()),
                    entity.getOrigin(), entity.getMaterial(), true, entity.getDescribe(), entity.getReview(),
                    manufacture, dateStr);
//            Product product = mapper.convertToEntity(entity);
//            product.setManuDay(dateStr);
//            product.setImage(param.save(entity.getImage()));
            productDao.save(product);
        }
        return "redirect:/admin/productForm/list";
        // return "manager/productForm";
    }

    @GetMapping("list/load/{id}")
    public String load(Model model, @PathVariable("id") Product product) {

        ProductModel pmodel = mapper.convertToDTO(product);
        model.addAttribute("edit3", true);
        model.addAttribute("productForm", pmodel);
        // session.set("nameImage2", manufacture.getImage());
        session.set("id3", product.getId());
        return "manager/productForm";
    }

    @GetMapping("list/enable/{id}")
    public String enable(Model model, @PathVariable("id") Product entity) {

        entity.setStatus(!entity.isStatus());
        productDao.save(entity);
        return "redirect:/admin/productForm/list";
    }

    @PostMapping("form/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id, @ModelAttribute("productForm") ProductModel entity,
                       BindingResult result) {
        model.addAttribute("edit3", true);

        if (!result.hasErrors()) {
            // Manufacture manufacture = dao.getById(id);
            System.out.println(entity.getManuDay());
            String dateStr = entity.getManuDay();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateStr = sdf.format(date);

            Product product = productDao.getById(id);

            product.setName(entity.getName());
            product.setPrice(entity.getPrice());
            product.setOrigin(entity.getOrigin());
            product.setMaterial(entity.getMaterial());
            product.setDescribe(entity.getDescribe());
            product.setReview(entity.getReview());
            product.setManuDay(dateStr);
            Manufacture manufacture = manuDao.getById(Integer.parseInt(entity.getManufacture()));
            product.setManufacture(manufacture);
            if (!entity.getImage().isEmpty()) {
                product.setImage(param.save(entity.getImage()));
            }
            productDao.save(product);
            model.addAttribute("edit3", false);
            // session.set("nameImage3", "");
            return "redirect:/admin/productForm/list";
        }

        return "manager/productForm";
    }
    @ModelAttribute("manufacture")
    public Map<Integer, String> manufacture() {
        Map<Integer, String> map = new HashMap();
        List<Manufacture> list = manuDao.findAll();
        for (Manufacture m : list) {
            map.put(m.getId(), m.getName());
        }
        return map;
    }


}
