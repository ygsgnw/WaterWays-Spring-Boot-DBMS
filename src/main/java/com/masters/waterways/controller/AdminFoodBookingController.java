package com.masters.waterways.controller;

import com.masters.waterways.daos.FoodBookingDao;
import com.masters.waterways.models.FoodBooking;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminFoodBookingController {

    FoodBookingDao foodBookingDao;

    @GetMapping("/admin/voyage/{voyageId}/foodbookings")
    public String foodBookingsList(@PathVariable int voyageId, Model model){
        model.addAttribute("food_bookings", foodBookingDao.getAllByVoyageId(voyageId));
        return "AdminFoodBookingList";
    }
}
