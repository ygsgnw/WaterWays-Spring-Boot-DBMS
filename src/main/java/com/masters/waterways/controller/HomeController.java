package com.masters.waterways.controller;

import com.masters.waterways.daos.userdao;
import com.masters.waterways.daos.voyagedao;
import com.masters.waterways.models.Voyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.masters.waterways.models.Users;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
public class HomeController {
	@Autowired
	private userdao userdao;
	@Autowired
	private voyagedao voyagedao;
	
	@GetMapping("/")
	public String home(){
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


	@PostMapping("/signup")
	public String signedup(@ModelAttribute("newUser") Users newUser) {
		userdao.save(newUser);
		return "redirect:/profile";
	}


//	@GetMapping("/profile/{id}")
//	public String profile(@PathVariable int id, Model model) {
////		System.out.println("p");
//		model.addAttribute("user", userdao.getbyid(id));
////		System.out.println("p");
//		return "profile";
//	}
//
//
//	@GetMapping("/profile/edit/{id}")
//	public String editProfile(@PathVariable int id, Model model) {
//		model.addAttribute("user", userdao.getbyid(id));
//		return "editProfile";
//	}
//
//
//	@PostMapping("/profile/edit/{id}")
//	public String updateProfile(@PathVariable int id,
//			@ModelAttribute("user") Users user,
//			Model model) {
//		userdao.update(user,id);
//		return "redirect:/profile/{id}";
//	}


	@GetMapping("/voyages")
	public String voyagesList(Model model,
			@RequestParam(name="fromid", required = false) Integer fromid,
			@RequestParam(name="toid", required = false) Integer toid,
			@RequestParam(name="fromdate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromdate,
			@RequestParam(name="todate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime todate) {
		System.out.println("hello");
		System.out.println(fromid);
		System.out.println(toid);
		System.out.println(fromdate);
		System.out.println(todate);

		List<Voyage> voyages = voyagedao.getall();
		Set<Voyage> new_voyages = null;
		if(fromid!=null) {
			for (Voyage v: voyages) {
				if (v.getDepartureHarborId() == fromid ){
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

	
}
