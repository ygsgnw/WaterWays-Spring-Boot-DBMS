package com.masters.waterways.controller;


import com.masters.waterways.daos.ShipModelDao;
import com.masters.waterways.models.ShipModel;
import com.masters.waterways.models.ShipStatusProvider;
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
	private ShipDao shipDao;

	@Autowired
	private ShipModelDao shipModelDao;

	@GetMapping("/admin/ship")
	public String listship(Model model) {
		model.addAttribute("shipstatuses", ShipStatusProvider.getShipStatusDesc);
		model.addAttribute("ships", shipDao.getAll());
		return "ShipList";
	}
	@GetMapping("/admin/ship/add")
	public String createcrewform(Model model) {
		model.addAttribute("shipmodels", shipModelDao.getAll());
		model.addAttribute("shipstatuses", ShipStatusProvider.getShipStatusCode);
		model.addAttribute("newship", new Ship());
		return "AddShipForm";
	}
	
	@PostMapping("/admin/ship/add")
	public String saveship(@ModelAttribute("newship") Ship newship) {
		System.out.println("ship");
			shipDao.insert(newship);
		return "redirect:/admin/ship";
	}
	
	@GetMapping("/admin/ship/update/{id}")
	public String editshipform(@PathVariable int id, Model model) {
		model.addAttribute("shipstatuses", ShipStatusProvider.getShipStatusCode);
		model.addAttribute("ship", shipDao.getById(id));
		return "UpdateShipForm";
	}
	
	@PostMapping("/admin/ship/update/{id}")
	public String updateship(@PathVariable int id,
			@ModelAttribute("ship") Ship ship,
			Model model) {
		shipDao.update(ship, id);
		return "redirect:/admin/ship";
	}

	
	
	
}
