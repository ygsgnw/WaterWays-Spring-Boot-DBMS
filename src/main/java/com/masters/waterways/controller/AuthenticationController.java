package com.masters.waterways.controller;

import com.masters.waterways.daos.UsersDao;
import com.masters.waterways.models.Users;
import com.masters.waterways.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsersDao usersDao;

    @GetMapping("/login")
    public String login(Model model, HttpSession session){
        if (authenticationService.isAuthenticated(session)) {
            if (authenticationService.isAdmin(session))
                return "redirect:/admin";
            else
                return "redirect:/user";
        }
        model.addAttribute("credentials", new Users());
        model.addAttribute("isCorrect",true);
        model.addAttribute("isUser",true);
        return "Login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute Users credentials, Model model, HttpSession session){
        if (authenticationService.isAuthenticated(session)) {
            if (authenticationService.isAdmin(session))
                return "redirect:/admin";
            else
                return "redirect:/user";
        }

        String emailId = credentials.getEmailId();
        String password = credentials.getUserPassword();

        if (authenticationService.authenticateUser(usersDao.getByEmailId(emailId), password, session)) {
            if (authenticationService.isAdmin(session))
                return "redirect:/admin";
            else
                return "redirect:/user";
        } else {
            model.addAttribute("credentials", new Users());
            model.addAttribute("isCorrect", false);
            model.addAttribute("isUser", false);
            return "Login";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        authenticationService.logoutUser(session);
        return "redirect:/";
    }




}
