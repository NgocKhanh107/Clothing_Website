package com.duan.controllers;

import com.duan.dto.StatisFavorite;
import com.duan.dto.StatisOrder;
import com.duan.repositories.FavoriteDao;
import com.duan.repositories.OrderDao;
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

import java.util.Optional;

@Controller
@RequestMapping("/admin/statis/")
public class StatisController {
    @Autowired
    SessionService sessionService;

    @Autowired
    FavoriteDao favoriteDao;

    @Autowired
    OrderDao orderDao;

    @GetMapping("favorite")
    public String favorite(Model model, @RequestParam("keyword13") Optional<String> name, @RequestParam("p") Optional<Integer> p) {
        String findName;
        if(sessionService.get("keyword13") == null) {
            findName = name.orElse("");
        }
        else {
            findName = name.orElse(sessionService.get("keyword13"));
        }

        sessionService.set("keyword13", findName);


        Pageable pageable = PageRequest.of(p.orElse(0), 5);

//        Page<StatisFavorite> list = favoriteDao.fillToTable("%" + findName + "%", pageable);
//        model.addAttribute("statisFavorite", list);
        model.addAttribute("location", 0);
        return "manager/statisFavorite";
    }

    @GetMapping("order")
    public String order(Model model, @RequestParam("keyword14") Optional<String> name, @RequestParam("p") Optional<Integer> p) {
        String findName;
        if(sessionService.get("keyword13") == null) {
            findName = name.orElse("");
        }
        else {
            findName = name.orElse(sessionService.get("keyword13"));
        }

        sessionService.set("keyword13", findName);


        Pageable pageable = PageRequest.of(p.orElse(0), 5);

//        Page<StatisOrder> list = orderDao.fillToTable("%" + findName + "%", pageable);
//        model.addAttribute("statisOrder", list);
        model.addAttribute("location", 0);
        return "manager/statisOrder";
    }
}
