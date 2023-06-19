package com.duan.controllers;

import com.duan.dto.UserModel;
import com.duan.entities.Employee;
import com.duan.entities.Role;
import com.duan.entities.UserRole;
import com.duan.entities.Users;
import com.duan.repositories.EmployeeDao;
import com.duan.repositories.RoleDao;
import com.duan.repositories.UserRoleDao;
import com.duan.repositories.userRepo;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/admin/userForm/")
public class UserAdminController {
    @Autowired
    userRepo dao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    SessionService session;

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    RoleDao roleDao;

    @GetMapping("form")
    public String index(Model model) {
        UserModel entity = new UserModel();
        model.addAttribute("userForm", entity);
        //model.addAttribute("check", null);
        return "manager/userForm";
    }
    @GetMapping("list")
    public String list(Model model, @RequestParam("keyword") Optional<String> name, @RequestParam("p") Optional<Integer> p) {
        String findName;
        if(session.get("keyword") == null) {
            findName = name.orElse("");
        }
        else {
            findName = name.orElse(session.get("keyword"));
        }

        session.set("keyword", findName);


        Pageable pageable = PageRequest.of(p.orElse(0), 5);

        Page<Users> list = dao.fillUser("%"+findName+"%", pageable);
        model.addAttribute("userItem", list);
        return "manager/userTable";
    }
    @GetMapping("edit/employee/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        Role role = roleDao.getOne(1);
        UserRole userRole = userRoleDao.getById(userRoleDao.findIdUserRole(id));
        userRole.setRole(role);
        userRoleDao.save(userRole);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        Employee employee = new Employee("Kỹ sư phần mềm", strDate, dao.getById(id));

        employeeDao.save(employee);

        return "redirect:/admin/userForm/list";
    }
}
