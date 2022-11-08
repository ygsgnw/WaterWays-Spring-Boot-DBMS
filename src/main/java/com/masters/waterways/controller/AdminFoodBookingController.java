package com.masters.waterways.controller;

import com.masters.waterways.daos.FoodBookingDao;
import com.masters.waterways.daos.FoodBookingViewDao;
import com.masters.waterways.models.FoodBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminFoodBookingController {

    @Autowired
    FoodBookingViewDao foodBookingViewDao;

    @GetMapping("/admin/voyage/{voyageId}/foodbookings")
    public String foodBookingsList(@PathVariable int voyageId, Model model){

        model.addAttribute("food_bookings", foodBookingViewDao.getAllByVoyageId(voyageId));

        return "AdminFoodBookingList";
    }
}
