package com.masters.waterways.controller;

import com.masters.waterways.daos.EmployeeDao;
import com.masters.waterways.daos.UsersDao;
import com.masters.waterways.models.Employee;
import com.masters.waterways.models.Ship;
import com.masters.waterways.models.ShipStatusProvider;
import com.masters.waterways.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private EmployeeDao employeeDao;


    @GetMapping("/admin/employee")
    public String listemployee(Model model) {
        model.addAttribute("empployeestatuses", EmployeeStatusProvider.getEmployeeStatusDesc);
        model.addAttribute("employees", employeeDao.getAll());
        return "EmployeeList";
    }

    @GetMapping("/admin/employee/add")
    public String createcrewform(Model model) {
        model.addAttribute("shipstatuses", EmployeeStatusProvider.getShipStatusCode);
        model.addAttribute("newship", new Ship());
        return "AddShipForm";
    }

    @PostMapping("/admin/employee/add")
    public String saveship(@ModelAttribute("newship") Ship newship) {
        System.out.println("ship");
        shipDao.insert(newship);
        return "redirect:/admin/ship";
    }

    @GetMapping("/admin/employee/update/{id}")
    public String editemployeeform(@PathVariable int id, Model model) {
        model.addAttribute("employeestatuses", EmployeeStatusProvider.getShipStatusCode);
        model.addAttribute("employee", employeeDao.getById(id));
        return "UpdateEmployeeForm";
    }

    @PostMapping("/admin/employee/update/{id}")
    public String updateemployee(@PathVariable int id,
                             @ModelAttribute("employee") Ship employee,
                             Model model) {
        employeeDao.update(employee, id);
        return "redirect:/admin/ship";
    }





}
