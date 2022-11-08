package com.masters.waterways.controller;

import com.masters.waterways.daos.UsersDao;
import com.masters.waterways.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminUserController {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/admin/users")
    public String userslist(Model model, HttpSession session) {

        if (!authenticationService.isAdmin(session))
            return "redirect:/login";

        model.addAttribute("users", usersDao.getAll());
        return "UsersList";
    }
}
