package com.masters.waterways.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.masters.waterways.models.Users;

@Controller
public class HomeController {
//	@Autowired
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("newUser", new Users());
		return "signup";
	}
	

	
}
