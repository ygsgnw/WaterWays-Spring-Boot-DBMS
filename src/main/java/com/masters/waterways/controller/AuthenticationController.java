package com.masters.waterways.controller;

import com.masters.waterways.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    private UserDao userDao;

    @Autowired
    private ToastService toastService;

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (authenticationService.isAuthenticated(session)) {
        	System.out.println(1);
            if(user){
                return "redirect:/user";
            }
            return "redirect:/admin";

        }

        model.addAttribute("credentials", new User());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute User credentials, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (authenticationService.isAuthenticated(session)) {
            System.out.println(1);
            if(user){
                return "redirect:/user";
            }
            return "redirect:/admin";

        }

        int id = credentials.getUser_id();
        String password = credentials.getUser_password();
        String errorMessage = null;
        System.out.println(id);
        System.out.println(password);
        try {
            if (authenticationService.checkCredentials(id, password)) {
                authenticationService.loginUser(session, id);
                System.out.println("done");

                toastService.redirectWithSuccessToast(redirectAttributes, "Successfully logged in.");
                return "redirect:/";
            }
            errorMessage = "Incorrect password.";
        } catch (Exception e) {
            errorMessage = "No user with this username found.";
        }
        System.out.println(1);

        model.addAttribute("credentials", credentials);
        toastService.displayErrorToast(model, errorMessage);
        System.out.println("log");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        authenticationService.logoutUser(session);
        return "redirect:/";
    }

//    @GetMapping("/lwada")
//    public String lwada() {
//
//        return "base";
//    }
}