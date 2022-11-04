package com.masters.waterways.controller;

import com.masters.waterways.daos.UsersDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminContoller {

    UsersDao usersDao;

    @GetMapping("/admin")
    public String adminHome(){
        return "AdminHome";
    }

    @GetMapping
    public String userslist(Model model){
        model.addAttribute("users", usersDao.getAll());
        return "UsersList";
    }
}
