package com.masters.waterways.controller;

import com.masters.waterways.daos.RoomBookingDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminRoomBookingController {

    RoomBookingDao roomBookingDao;

    @GetMapping("/admin/voyage/{voyageId}/roombookings")
    public String roomBookingList(@PathVariable("voyageId") int voyageId, Model model){
        model.addAttribute("room_bookings", roomBookingDao.getAllByVoyageId(voyageId));
        return "AdminRoomBookingList";
    }
}
