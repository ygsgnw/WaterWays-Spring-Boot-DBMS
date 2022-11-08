package com.masters.waterways.controller;

import com.masters.waterways.daos.RoomBookingDao;
import com.masters.waterways.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class AdminRoomBookingController {

    @Autowired
    RoomBookingDao roomBookingDao;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/admin/voyage/{voyageId}/roombookings")
    public String roomBookingList(@PathVariable("voyageId") int voyageId, Model model, HttpSession session) {

        if (!authenticationService.isAdmin(session))
            return "redirect:/login";

        model.addAttribute("room_bookings", roomBookingDao.getAllByVoyageId(voyageId));
        return "AdminRoomBookingList";
    }
}
