package com.masters.waterways.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AdminContoller {






    @GetMapping("/admin")
    public String adminHome(){
        return "UserHome";
    }


}
