package com.masters.waterways.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.masters.waterways.daos.shipmodeldao;
import com.masters.waterways.models.ShipModel;
@Controller
public class ShipModelController {
	@Autowired
	private shipmodeldao modeldao;
	
	@GetMapping("/admin/shipmodel")
	public String listshipmodel(Model model) {
		model.addAttribute("shipmodels", modeldao.getall());
		return "shipmodelList";
	}
	@GetMapping("/admin/shipmodel/new")
	public String createshipmodelform(Model model) {
		ShipModel newshipmodel =new ShipModel();
		model.addAttribute("newshipmodel", newshipmodel);
		return "createshipmodelform";
	}
	
	@PostMapping("/admin/shipmodel")
	public String saveshimodel(@ModelAttribute("newshipmodel") ShipModel newshipmodel) {
		modeldao.save(newshipmodel);	
		return "redirect:/admin/shipmodel";
	}
	
	@GetMapping("/admin/shipmodel/edit/{id}")
	public String editshipmodelform(@PathVariable int id, Model model) {
		model.addAttribute("shipmodel", modeldao.getbyid(id));
		return "editshipmodelform";
	}
	
	@PostMapping("/admin/shipmodel/edit/{id}")
	public String updateshipmodel(@PathVariable int id,
			@ModelAttribute("shipmodel") ShipModel shipmodel,
			Model model) {
		
		modeldao.update(shipmodel, id);
		return "redirect:/admin/shipmodel";
	}
	
	@GetMapping("/admin/shipmodel/delete/{id}")
	public String deleteshipmodel(@PathVariable int id) {
		modeldao.delete(id);
		return "redirect:/admin/shipmodel";
	}
	
}
