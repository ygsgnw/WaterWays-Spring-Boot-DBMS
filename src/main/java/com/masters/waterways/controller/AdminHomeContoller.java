package com.masters.waterways.controller;

import com.masters.waterways.daos.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeContoller {

    @Autowired
    UsersDao usersDao;

    @GetMapping("/admin")
    public String adminHome(){
        return "AdminHome";
    }

}
