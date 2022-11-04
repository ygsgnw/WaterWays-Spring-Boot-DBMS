package com.masters.waterways.controller;

import com.masters.waterways.daos.EmployeeDao;
import com.masters.waterways.daos.UsersDao;
import com.masters.waterways.models.Employee;
import com.masters.waterways.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmployeeController {
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping("/admin/employee")
    public String listvoyages(Model model) {
        model.addAttribute("users", usersDao.getAllNewRecruiter());
        return "EmployeeRecruit";
    }
    @GetMapping("/admin/employeerecruit/add/{id}")
    public String deletevoyage(@PathVariable int id) {
        Users newrecruit = usersdao.getById(id);
        Employee newemployee = new Employee();
        newemployee.setUserId(newrecruit.getUserId());
        newemployee.setEmployeeId(0);   //DataBase Auto Increament
        employeedao.insert(newemployee);
        return "redirect:/admin/voyage";
    }

}
