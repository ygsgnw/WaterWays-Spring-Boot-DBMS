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
        if(authenticationService.isAuthenticated(session)){
            return "redirect:/";
        }
        model.addAttribute("credentials", new Users());
        model.addAttribute("isCorrect",true);
        model.addAttribute("isUser",true);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute Users credentials, Model model, HttpSession session, RedirectAttributes redirectAttributes){
        if(authenticationService.isAuthenticated(session)){
            return "redirect:/";
        }

        String userID = credentials.getEmailId();
        String password = credentials.getUserPassword();

        boolean isCorrect = true;
        boolean isUser = true;
        try {
            Users user = usersDao.getByEmailId(userID);
            if(authenticationService.checkCredentials(user.getUserId(),password)){
                authenticationService.loginUser(session,user.getUserId());

                if(authenticationService.isAdmin(session)){
                    return "redirect:/admin";
                }

                return "redirect:/user";

            }
            isCorrect = false;
            System.out.println("Incorrect Password");
        }
        catch (Exception e){
            isUser = false;
            System.out.println("User Not exist");
        }
        model.addAttribute("credentials",new Users());
        model.addAttribute("isCorrect",isCorrect);
        model.addAttribute("isUser",isUser);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        authenticationService.logoutUser(session);
        return "redirect:/";
    }
//
//    @GetMapping("/createEmployee")
//    public String getEmployee(Model model, HttpSession session){
//        if(authenticationService.isAdmin(session)){
//            Employee employee = new Employee();
//            model.addAttribute("employee", new Employee());
//            return "createEmployee";
//        }
//        return "redirect:/login";
//    }



}
