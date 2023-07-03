package com.duan.controllers;

import com.duan.dto.ColorModel;
import com.duan.entities.Color;
import com.duan.repositories.ColorDao;
import com.duan.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/admin/colorForm/")
public class ColorController {
    @Autowired
    ColorDao dao;

    @Autowired
    SessionService session;

    @GetMapping("form")
    public String index(Model model) {
        ColorModel entity = new ColorModel();
        model.addAttribute("colorModel", entity);
        return "manager/colorForm";
    }
    @GetMapping("list")
    public String list(Model model, @RequestParam("keyword7") Optional<String> name,
                       @RequestParam("p") Optional<Integer> p) {
        String findName;
        if (session.get("keyword7") == null) {
            findName = name.orElse("");
        } else {
            findName = name.orElse(session.get("keyword7"));
        }

        session.set("keyword7", findName);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);

        Page<Color> page = dao.findByNameLike("%" + findName + "%", pageable);
        model.addAttribute("colorItem", page);

        return "manager/colorTable";
    }
    @GetMapping("list/delete/{id}")
    public String delete(Model model, @PathVariable("id") int id) {
        Color entity = dao.getById(id);
        dao.delete(entity);
        return "redirect:/admin/colorForm/list";
    }
}
