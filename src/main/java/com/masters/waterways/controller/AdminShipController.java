package com.masters.waterways.controller;


import com.masters.waterways.daos.ShipAdminViewDao;
import com.masters.waterways.daos.ShipModelDao;
import com.masters.waterways.models.Ship;
import com.masters.waterways.models.ShipStatusProvider;
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
public class AdminShipController {
	@Autowired
	private ShipDao shipDao;

	@Autowired
	private ShipModelDao shipModelDao;

	@Autowired
	private ShipAdminViewDao shipAdminViewDao;

	@GetMapping("/admin/ship")
	public String listship(Model model) {
		model.addAttribute("shipstatuses", ShipStatusProvider.getShipStatusDesc);
		model.addAttribute("ships", shipAdminViewDao.getAll());
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
	
//	@GetMapping("/admin/ship/{id}/update")
//	public String editshipform(@PathVariable int id, Model model) {
//		model.addAttribute("shipstatuses", ShipStatusProvider.getShipStatusCode);
//		Ship ship =  shipDao.getById(id);
//		ship
//		model.addAttribute("ship", shipDao.getById(id));
//		System.out.println(ship.getMfDate());
//		System.out.println(ship.getShipSerialId());
//		System.out.println(id);
//		return "UpdateShipForm";
//	}
//
//	@PostMapping("/admin/ship/{id}/update")
//	public String updateship(@PathVariable int id,
//			@ModelAttribute("ship") Ship ship,
//			Model model) {
//		shipDao.update(ship, id);
//		return "redirect:/admin/ship";
//	}

	@GetMapping("/admin/ship/{id}/update")
	public String updateShipStatus(@PathVariable int id, Model model) {
		Ship ship=shipDao.getById(id);
		if (ShipStatusProvider.getShipStatusDesc.get(ship.getShipStatusCode()).equals("SUSPENDED")){
			shipDao.setActive(id);
		}
		else {
			shipDao.setSuspended(id);
		}
		return "redirect:/admin/ship";
	}
	
	
}
