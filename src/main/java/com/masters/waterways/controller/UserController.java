package com.masters.waterways.controller;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.FoodBooking;
import com.masters.waterways.models.Users;
import com.masters.waterways.models.Voyage;
import com.masters.waterways.models.VoyageUserView;
import com.masters.waterways.services.AuthenticationService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller

public class UserController {

    @Autowired
    private UsersDao usersDao;

    @Autowired
     private VoyageDao voyageDao;

    @Autowired
    private RoomBookingDao roomBookingDao;

    @Autowired
    private FoodItemDao foodItemDao;
    
    @Autowired
    private FoodBookingDao foodBookingDao;

    @Autowired
    private VoyageUserViewDao voyageUserViewDao;

    @Autowired
    AuthenticationService authenticationService;
    
    @GetMapping("/user")
    public String userhome(Model model, HttpSession session){
        int id = authenticationService.getCurrentUser(session);
        Users user = usersDao.getById(id);
        model.addAttribute("user",user);
        return "UserHome";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        model.addAttribute("user", usersDao.getById(authenticationService.getCurrentUser(session)));
        return "Profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model, HttpSession session) {
        model.addAttribute("user", usersDao.getById(authenticationService.getCurrentUser(session)));
        return "EditProfile";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute("user") Users user, Model model, HttpSession session) {
        usersDao.update(user, authenticationService.getCurrentUser(session));
        return "redirect:/profile";
    }

//    @GetMapping("/user/voyages")
//    public String voyagesListUser (Model model,
//        @RequestParam(name="fromid", required = false) Integer from_harbour_id,
//        @RequestParam(name="toid", required = false) Integer to_harbour_id,
//        @RequestParam(name="fromdate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime depart_after_datetime,
//        @RequestParam(name="todate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrive_before_datetime
//    ) {
//
//        System.out.println(from_harbour_id);
//        System.out.println(to_harbour_id);
//        System.out.println(depart_after_datetime);
//        System.out.println(arrive_before_datetime);
//
//        List<Voyage> voyages = voyageDao.getAll();
//
//        System.out.println("hi");
//
//        if (from_harbour_id != null) {
//            List<Voyage> new_voyages = new ArrayList<>();
//            for (Voyage v: voyages)
//                if (v.getDepartureHarborId() == from_harbour_id)
//                    new_voyages.add(v);
//            voyages = new_voyages;
//        }
//
//        if (to_harbour_id != null) {
//            List<Voyage> new_voyages = new ArrayList<>();
//            for (Voyage v: voyages)
//                if (v.getArrivalHarborId() == to_harbour_id)
//                    new_voyages.add(v);
//            voyages = new_voyages;
//        }
//
//        if (depart_after_datetime != null) {
//            List<Voyage> new_voyages = new ArrayList<>();
//            for (Voyage v: voyages)
//                if (v.getDepartureTime().isAfter(depart_after_datetime))
//                    new_voyages.add(v);
//            voyages = new_voyages;
//        }
//
//        if (arrive_before_datetime != null) {
//            List<Voyage> new_voyages = new ArrayList<>();
//            for (Voyage v: voyages)
//                if (v.getArrivalTime().isBefore(arrive_before_datetime))
//                    new_voyages.add(v);
//            voyages = new_voyages;
//        }
//
//        System.out.println(voyages.size());
//        model.addAttribute("newVoyages", voyages);
//
//        return "VoyageListUser";
//    }
//
//    @GetMapping("/user/voyages/{userId}")
//    public String voyagesDetails(@PathVariable("id") int voyageId, Model model, HttpSession session) {
//
//        model.addAttribute("voyage", voyageDao.getById(authenticationService.getCurrentUser(session)));
//        model.addAttribute("rooms", roomBookingDao.getAllByUserIdAndVoyageId(authenticationService.getCurrentUser(session), voyageId));
//        model.addAttribute("foodItems", foodItemDao.getAll());
//
//        return "VoyageDetailsUser";
//    }

    @GetMapping("/user/booking/{id}")
    public String booking(@PathVariable("id") int voyageId, HttpSession session){
//        int userId=session_key;

        roomBookingDao.bookRoomByVoyageIdAndUserId(authenticationService.getCurrentUser(session), voyageId);
        return "redirect:/voyages/{id}";
    }
    
    @GetMapping("/user/mybookings")
    public String mybookings(Model model, HttpSession session){
//        int userId=session_key;

        model.addAttribute("my_completed_voyages", voyageUserViewDao.getAllCompletedByUserId(authenticationService.getCurrentUser(session)));
        model.addAttribute("my_upcoming_voyages", voyageUserViewDao.getAllActiveByUserId(authenticationService.getCurrentUser(session)));
        return "MyVoyageList"; // will direct to VoyageDetailsUser
    }

    @GetMapping("/user/voyage/{voyageId}/room/{roomId}")
    public String foodBookingForm(@PathVariable("voyageId") int voyageId, @PathVariable("roomId") int roomId, Model model){
        FoodBooking foodBooking = new FoodBooking();
        foodBooking.setRoomId(roomId);
        foodBooking.setVoyageId(voyageId);
        model.addAttribute("food_booking", foodBooking);
        return "FoodBookingForm";
    }

    @PostMapping("/user/foodbooking")
    public String foodBookingForRoom (@ModelAttribute("foodBooking")FoodBooking foodBooking,
                                      HttpSession session
    ) {
        foodBookingDao.bookFood(authenticationService.getCurrentUser(session), foodBooking);
        return "redirect:/voyage/{voyageId}";
    }
    
}

