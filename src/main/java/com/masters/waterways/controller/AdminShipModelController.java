package com.masters.waterways.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.masters.waterways.daos.ShipModelDao;
import com.masters.waterways.models.ShipModel;
@Controller
public class AdminShipModelController {
	@Autowired
	private ShipModelDao shipModelDao;
	
	@GetMapping("/admin/shipmodel")
	public String listshipmodel(Model model) {
		model.addAttribute("shipmodels", shipModelDao.getAll());
		return "ShipModelList";
	}
	@GetMapping("/admin/shipmodel/add")
	public String createshipmodelform(Model model) {
		ShipModel newshipmodel =new ShipModel();
		model.addAttribute("newshipmodel", newshipmodel);
		return "createShipModelForm";
	}
	
	@PostMapping("/admin/shipmodel/add")
	public String saveshimodel(@ModelAttribute("newshipmodel") ShipModel newshipmodel) {
		shipModelDao.insert(newshipmodel);
		return "redirect:/admin/shipmodel";
	}
	
	@GetMapping("/admin/shipmodel/update/{id}")
	public String editshipmodelform(@PathVariable int id, Model model) {
		model.addAttribute("shipmodel", shipModelDao.getById(id));
		return "editShipModelForm";
	}
	
	@PostMapping("/admin/shipmodel/update/{id}")
	public String updateshipmodel(@PathVariable int id,
			@ModelAttribute("shipmodel") ShipModel shipmodel,
			Model model) {
		
		shipModelDao.update(shipmodel, id);
		return "redirect:/admin/shipmodel";
	}
	
}
