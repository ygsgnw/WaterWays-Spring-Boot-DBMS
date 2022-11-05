package com.masters.waterways.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminFoodBookingController {
    @GetMapping("/admin/foodbookings")
    public String foodBookingsList(Model model){
        model.addAttribute("foodbookings", )
    }
}
