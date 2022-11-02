package com.masters.waterways.controller;

import com.masters.waterways.daos.userdao;
import com.masters.waterways.daos.voyagedao;
import com.masters.waterways.models.Users;
import com.masters.waterways.models.Voyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Controller

public class UserController {

    @Autowired
    private com.masters.waterways.daos.userdao userdao;

    @Autowired
    private com.masters.waterways.daos.voyagedao voyagedao;

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable int id, Model model) {
//		System.out.println("p");
        model.addAttribute("user", userdao.getbyid(id));
//		System.out.println("p");
        return "profile";
    }

    @GetMapping("/profile/edit/{id}")
    public String editProfile(@PathVariable int id, Model model) {
        model.addAttribute("user", userdao.getbyid(id));
        return "editProfile";
    }

    @PostMapping("/profile/edit/{id}")
    public String updateProfile(@PathVariable int id,
                                @ModelAttribute("user") Users user,
                                Model model) {
        userdao.update(user,id);
        return "redirect:/profile/{id}";
    }

    @GetMapping("/user/voyages")
    public String voyagesList(Model model,
                              @RequestParam(name="from", required = false) Integer from,
                              @RequestParam(name="to", required = false) Integer to,
                              @RequestParam(name="date", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        System.out.println("hello");
        System.out.println(from);
        System.out.println(to);
        System.out.println(date);

        List<Voyage> voyages = voyagedao.getall();
        Set<Voyage> new_voyages = null;
        if(from!=null) {
            for (Voyage v: voyages) {
                if (v.getDepartureHarborId() == from ){
                    new_voyages.add(v);
                }

            }
        }
        if(to!=null){
            for (Voyage v: voyages) {
                if (v.getArrivalHarborId() == to ){
                    new_voyages.add(v);
                }

            }
        }
        if(date!=null){
            for (Voyage v: voyages) {
                if (v.getDepartureTime().toLocalDate().equals(date) ){
                    new_voyages.add(v);
                }
            }
        }
        if(from==null && to==null && date==null){
            for (Voyage v: voyages) {
                new_voyages.add(v);
            }
        }
        model.addAttribute("new_voyage", new_voyages);

        return "voyageList";
    }

    @GetMapping("/user/booking")
    String book()
}
