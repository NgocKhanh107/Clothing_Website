package com.duan.controllers;

import com.duan.dto.Categories;
import com.duan.entities.Category;
import com.duan.repositories.CategoryDao;
import com.duan.service.ParamService;
import com.duan.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/categoryForm/")
public class CategoryController {
    @Autowired
    CategoryDao dao;

    @Autowired
    SessionService session;

    @Autowired
    ParamService param;

    @GetMapping("form")
    public String index(Model model) {
        Categories entity = new Categories();
        model.addAttribute("categoryForm", entity);
        return "manager/categoryForm";
    }

    @GetMapping("list")
    public String index(Model model, @RequestParam("keyword3") Optional<String> name,
                        @RequestParam("p") Optional<Integer> p) {
        String findName;
        if (session.get("keyword3") == null) {
            findName = name.orElse("");
        } else {
            findName = name.orElse(session.get("keyword3"));
        }

        session.set("keyword3", findName);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);

        Page<Category> page = dao.findByNameLike("%" + findName + "%", pageable);
        model.addAttribute("userItem", page);

        return "manager/categoryTable";
    }

    @GetMapping("list/load/{id}")
    public String load(Model model, @PathVariable("id") int id) {
        Category category = dao.getById(id);
        Categories entity = new Categories();
        entity.setName(category.getName());
        model.addAttribute("edit", true);
        model.addAttribute("categoryForm", entity);
        session.set("nameImage", category.getImage());
        session.set("id", category.getId());
        return "manager/categoryForm";
    }

    @GetMapping("form/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id, @ModelAttribute("categoryForm") Categories entity,
                       BindingResult result) {
        model.addAttribute("edit", true);
        if (entity.getName().isEmpty()) {
            result.rejectValue("name", "NotBlank.categoryForm.name");
        } else {
            Category category = dao.getById(id);
            category.setName(entity.getName());
            if (!entity.getImage().isEmpty()) {
                category.setImage(param.save(entity.getImage()));
            }
            dao.save(category);
            model.addAttribute("edit", false);
            session.set("nameImage", "");
            return "redirect:/admin/categoryForm/list";
        }

        return "manager/categoryForm";
    }

}
