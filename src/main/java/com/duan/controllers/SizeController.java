package com.duan.controllers;

import com.duan.dto.SizeModel;
import com.duan.entities.Size;
import com.duan.repositories.SizeDao;
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
@RequestMapping("/admin/sizeForm/")
public class SizeController {
    @Autowired
    SizeDao dao;

    @Autowired
    SessionService session;

    @GetMapping("form")
    public String index(Model model) {

        SizeModel entity = new SizeModel();

        model.addAttribute("sizeModel", entity);

        return "manager/sizeForm";
    }
    @GetMapping("list")
    public String list(Model model, @RequestParam("keyword6") Optional<String> name,
                       @RequestParam("p") Optional<Integer> p) {
        String findName;
        if (session.get("keyword6") == null) {
            findName = name.orElse("");
        } else {
            findName = name.orElse(session.get("keyword6"));
        }

        session.set("keyword6", findName);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);

        Page<Size> page = dao.fillToTable("%" + findName + "%", pageable);
        model.addAttribute("sizeItem", page);

        return "manager/sizeTable";
    }

    @GetMapping("delete/{id}")
    public String delete(Model model, @PathVariable("id") int id) {
        Size entity = dao.getById(id);
        dao.delete(entity);
        return "redirect:/admin/sizeForm/list";
    }
}
