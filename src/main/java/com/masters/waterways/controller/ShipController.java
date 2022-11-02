package com.masters.waterways.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.masters.waterways.daos.shipdao;
import com.masters.waterways.models.Ship;

@Controller
public class ShipController {
	@Autowired
	private shipdao shipdao;
	
	@GetMapping("/admin/ship")
	public String listship(Model model) {
		model.addAttribute("ships", shipdao.getall());
		return "shipList";
	}
	@GetMapping("/admin/ship/new")
	public String createcrewform(Model model) {
		Ship newship =new Ship();
		model.addAttribute("newship", newship);
		return "createshipform";
	}
	
	@PostMapping("/admin/ship/new")
	public String saveship(@ModelAttribute("newship") Ship newship) {
		System.out.println("ship");
		shipdao.save(newship);	
		return "redirect:/admin/ship";
	}
	
	@GetMapping("/admin/ship/edit/{id}")
	public String editshipform(@PathVariable int id, Model model) {
		model.addAttribute("ship", shipdao.getbyid(id));
		return "editshipform";
	}
	
	@PostMapping("/admin/ship/edit/{id}")
	public String updateship(@PathVariable int id,
			@ModelAttribute("ship") Ship ship,
			Model model) {
		shipdao.update(ship, id);
		return "redirect:/admin/ship";
	}
	
	@GetMapping("/admin/ship/delete/{id}")
	public String deleteship(@PathVariable int id) {
		shipdao.delete(id);
		return "redirect:/admin/ship";
	}
	
	
	
	
}
