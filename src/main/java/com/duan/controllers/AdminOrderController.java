package com.duan.controllers;

import com.duan.dto.OrderModel;
import com.duan.entities.Order;
import com.duan.repositories.OrderDao;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/order/")
public class AdminOrderController {
    @Autowired
    SessionService sessionService;

    @Autowired
    OrderDao orderDao;

    @GetMapping("wait")
    public String index(Model model, @RequestParam("keyword11") Optional<String> name, @RequestParam("p") Optional<Integer> p) {
        //User user = sessionService.get("userLogin");
        String findName;
        if(sessionService.get("keyword11") == null) {
            findName = name.orElse("");
        }
        else {
            findName = name.orElse(sessionService.get("keyword11"));
        }

        sessionService.set("keyword11", findName);


        Pageable pageable = PageRequest.of(p.orElse(0), 5);

        Page<OrderModel> list = orderDao.fillTableWOrder2("%" + findName + "%", pageable);
        model.addAttribute("orderWait", list);
        return "manager/orderWTable";
    }

    @GetMapping("wait/send/{name}")
    public String send(Model model, @PathVariable("name") String name) {
        List<Order> list = orderDao.findByNameLike(name);
        for(Order o: list) {
            o.setStatus(true);
            orderDao.save(o);
        }
        return "redirect:/admin/order/wait";
    }
    @GetMapping("/admin/orderDetail/{name}")
    public String detail(Model model, @PathVariable("name") String name) {
        List<Order> list = orderDao.findByNameLike(name);
        model.addAttribute("orderDetail", list);
        model.addAttribute("location", 0);
        return "manager/orderDetail";
    }

    @GetMapping("send")
    public String sending(Model model, @RequestParam("keyword12") Optional<String> name, @RequestParam("p") Optional<Integer> p) {
        //User user = sessionService.get("userLogin");
        String findName;
        if(sessionService.get("keyword12") == null) {
            findName = name.orElse("");
        }
        else {
            findName = name.orElse(sessionService.get("keyword12"));
        }

        sessionService.set("keyword12", findName);


        Pageable pageable = PageRequest.of(p.orElse(0), 5);

        Page<OrderModel> list = orderDao.fillTableWOrder2("%" + findName + "%", pageable);
        model.addAttribute("orderSend", list);
        return "manager/orderSTable";
    }
}
