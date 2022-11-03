package com.masters.waterways.controller;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.Users;
import com.masters.waterways.models.Voyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller

public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private VoyageDao voyageDao;

    @Autowired
    private RoomBookingDao roomBookingDao;

    @Autowired
    private FoodItemDao foodItemDao;

    @Autowired
    private FoodBookingDao foodBookingDao;

    int userId=session_key;

    @GetMapping("/user")
    public String userhome(){
        return "UserHome";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("user", userDao.getbyid(id));
        return "Profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        model.addAttribute("user", userDao.getbyid(id));
        return "EditProfile";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute("user") Users user, Model model) {
        userDao.update(user,id);
        return "redirect:/profile";
    }

    @GetMapping("/user/voyages")
    public String voyagesListUser (Model model,
                                   @RequestParam(name="fromid", required = false) Integer from_harbour_id,
                                   @RequestParam(name="toid", required = false) Integer to_harbour_id,
                                   @RequestParam(name="fromdate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime depart_after_datetime,
                                   @RequestParam(name="todate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrive_before_datetime
    ) {

        System.out.println("hello");
        System.out.println(from_harbour_id);
        System.out.println(to_harbour_id);
        System.out.println(depart_after_datetime);
        System.out.println(arrive_before_datetime);

        List<Voyage> voyages = voyageDao.getall();

        if (from_harbour_id != null) {
            List<Voyage> new_voyages = new ArrayList<>();
            for (Voyage v: voyages)
                if (v.getDepartureHarborId() == from_harbour_id)
                    new_voyages.add(v);
            voyages = new_voyages;
        }

        if (to_harbour_id != null) {
            List<Voyage> new_voyages = new ArrayList<>();
            for (Voyage v: voyages)
                if (v.getArrivalHarborId() == to_harbour_id)
                    new_voyages.add(v);
            voyages = new_voyages;
        }

        if (depart_after_datetime != null) {
            List<Voyage> new_voyages = new ArrayList<>();
            for (Voyage v: voyages)
                if (v.getDepartureTime().isAfter(depart_after_datetime))
                    new_voyages.add(v);
            voyages = new_voyages;
        }

        if (arrive_before_datetime != null) {
            List<Voyage> new_voyages = new ArrayList<>();
            for (Voyage v: voyages)
                if (v.getArrivalTime().isBefore(arrive_before_datetime))
                    new_voyages.add(v);
            voyages = new_voyages;
        }

        model.addAttribute("new_voyage", voyages);

        return "VoyageListUser";
    }

    @GetMapping("/user/voyages/{id}")
    public String voyagesDetails(@PathVariable("id") int voyageId, Model model) {

        model.addAttribute("voyage", voyageDao.getbyid(id));
        model.addAttribute("rooms", roomBookingDao.getRoomsByUserIdAndVoyageId(id,voyageId));
        model.addAttribute("foodItems", foodItemDao.getall());
        return "VoyageDetailsUser";
    }

    @GetMapping("/user/booking/{id}")
    public String booking(@PathVariable("id") int voyageId){
//        int userId=session_key;
        roomBookingDao.bookRoomByVoyageIdAndUserId(userId, voyageId);
        return "redirect:/user/voyages/{voyageId}";
    }

    @GetMapping("/user/mybookings")
    public String mybookings(Model model){
//        int userId=session_key;
        model.addAttribute("mybookings", voyageDao.getVoyagesByUserId(userId));
        return "MyVoyages"; // will direct to VoyageDetailsUser
    }

    @GetMapping("/user/foodbooking")
    public String voyagesListUser (@RequestParam(name="voyageId", required = false) int voyageId,
                                   @RequestParam(name="roomId", required = false) int roomId,
                                   @RequestParam(name="foodItemId", required = false) int foodItemId,
                                   @RequestParam(name="foodCount", required = false) int foodCount
    ) {
        foodBookingDao.bookFood(userId, voyageId, roomId, foodItemId, foodCount);
        return "redirect:/user/voyages/{voyageId}";
    }
}

