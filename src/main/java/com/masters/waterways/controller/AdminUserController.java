package com.masters.waterways.controller;

import com.masters.waterways.daos.UsersDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminUserController {

    private UsersDao usersDao;

    @GetMapping("/admin/users")
    public String userslist(Model model){
        model.addAttribute("users", usersDao.getAll());
        return "UsersList";
    }
}
