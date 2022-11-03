package com.masters.waterways.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.masters.waterways.daos.ShipDao;
import com.masters.waterways.models.Ship;

@Controller
public class ShipController {
	@Autowired
	private ShipDao shipdao;
	
	@GetMapping("/admin/ship")
	public String listship(Model model) {
		model.addAttribute("ships", shipdao.getAll());
		return "ShipList";
	}
	@GetMapping("/admin/ship/new")
	public String createcrewform(Model model) {
		Ship newship =new Ship();
		model.addAttribute("newship", newship);
		return "createShipForm";
	}
	
	@PostMapping("/admin/ship/new")
	public String saveship(@ModelAttribute("newship") Ship newship) {
		System.out.println("ship");
		shipdao.insert(newship);
		return "redirect:/admin/ship";
	}
	
	@GetMapping("/admin/ship/edit/{id}")
	public String editshipform(@PathVariable int id, Model model) {
		model.addAttribute("ship", shipdao.getById(id));
		return "editShipForm";
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
