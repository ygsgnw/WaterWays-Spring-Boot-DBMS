package com.masters.waterways.controller;

import com.masters.waterways.daos.FoodBookingDao;
import com.masters.waterways.daos.FoodBookingViewDao;
import com.masters.waterways.models.FoodBooking;
import com.masters.waterways.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class AdminFoodBookingController {

    @Autowired
    FoodBookingViewDao foodBookingViewDao;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/admin/voyage/{voyageId}/foodbookings")
    public String foodBookingsList(@PathVariable int voyageId, Model model, HttpSession session) {

        if (!authenticationService.isAdmin(session))
            return "redirect:/login";

        model.addAttribute("food_bookings", foodBookingViewDao.getAllByVoyageId(voyageId));

        return "AdminFoodBookingList";
    }
}
