package com.duan.controllers;

import com.duan.dto.ManufactureModel;
import com.duan.entities.Manufacture;
import com.duan.repositories.ManufactureDao;
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

import java.util.Optional;

@Controller
@RequestMapping("/admin/manuForm/")
public class ManufactureController {
    @Autowired
    SessionService session;

    @Autowired
    ManufactureDao dao;

    @Autowired
    ParamService param;

    @GetMapping("form")
    public String index(Model model) {
        session.set("nameImage2", "");
        ManufactureModel entity = new ManufactureModel();
        model.addAttribute("manuForm", entity);
        return "manager/manuForm";
    }
    @GetMapping("list")
    public String index(Model model, @RequestParam("keyword4") Optional<String> name,
                        @RequestParam("p") Optional<Integer> p) {
        String findName;
        if (session.get("keyword4") == null) {
            findName = name.orElse("");
        } else {
            findName = name.orElse(session.get("keyword4"));
        }

        session.set("keyword4", findName);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);

        Page<Manufacture> page = dao.findByNameLike("%" + findName + "%", pageable);
        model.addAttribute("manuItem", page);

        return "manager/manuTable";
    }
    @GetMapping("list/load/{id}")
    public String load(Model model, @PathVariable("id") int id) {
        Manufacture manufacture = dao.getById(id);
        ManufactureModel entity = new ManufactureModel();
        entity.setName(manufacture.getName());
        entity.setInformation(manufacture.getInformation());
        model.addAttribute("edit2", true);
        model.addAttribute("manuForm", entity);
        session.set("nameImage2", manufacture.getImage());
        session.set("id2", manufacture.getId());
        return "manager/manuForm";
    }
    @PostMapping("form/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id, @ModelAttribute("manuForm") ManufactureModel entity,
                       BindingResult result) {
        model.addAttribute("edit2", true);
        if (entity.getName().isEmpty()) {
            result.rejectValue("name", "NotBlank.manuForm.name");
        }
        if(entity.getInformation().isEmpty()) {
            result.rejectValue("information", "NotBlank.manuForm.information");
        }
        if(!result.hasErrors()) {
            Manufacture manufacture = dao.getById(id);
            manufacture.setName(entity.getName());
            manufacture.setInformation(entity.getInformation());
            if (!entity.getImage().isEmpty()) {
                manufacture.setImage(param.save(entity.getImage()));
            }
            dao.save(manufacture);
            model.addAttribute("edit2", false);
            session.set("nameImage2", "");
            return "redirect:/admin/manuForm/list";
        }

        return "manager/manuForm";
    }
    @PostMapping("form")
    public String save(Model model, @ModelAttribute("manuForm") @Validated ManufactureModel entity,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "manager/manuForm";
        }

        else {
            Manufacture manufacture = new Manufacture(entity.getName(), param.save(entity.getImage()), entity.getInformation());
            dao.save(manufacture);
        }
        return "redirect:/admin/manuForm/list";
    }
}
