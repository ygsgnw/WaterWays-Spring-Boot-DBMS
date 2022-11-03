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

    private UsersDao usersDao;


    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (authenticationService.isAuthenticated(session)) {
        	System.out.println(1);
            if(user){
                return "redirect:/user";
            }
            return "redirect:/admin";

        }

        model.addAttribute("credentials", new Users());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute Users credentials, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (authenticationService.isAuthenticated(session)) {
            if(user){
                return "redirect:/user";
            }
            return "redirect:/admin";

        }

        int id = credentials.getUserId();
        String password = credentials.getUserPassword();
        String errorMessage = null;
//        System.out.println(id);
//        System.out.println(password);
        try {
            if (authenticationService.checkCredentials(id, password)) {
                authenticationService.loginUser(session, id);
                if(user){
                    return "redirect:/user";
                }
                return "redirect:/admin";
            }
            errorMessage = "Incorrect password.";
        } catch (Exception e) {
            errorMessage = "No user with this username found.";
        }
//        System.out.println(1);

        model.addAttribute("credentials", credentials);
        System.out.println("log");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        authenticationService.logoutUser(session);
        return "redirect:/";
    }

}