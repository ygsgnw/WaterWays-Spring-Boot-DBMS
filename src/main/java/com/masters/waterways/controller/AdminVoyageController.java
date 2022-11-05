package com.masters.waterways.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.masters.waterways.daos.VoyageDao;
import com.masters.waterways.models.Voyage;
@Controller
public class AdminVoyageController {
	@Autowired
	
	private VoyageDao voyagedao;
	
	@GetMapping("/admin/voyage")
	public String listvoyages(Model model) {
		model.addAttribute("voyages", voyagedao.getAll());
		return "VoyageList";
	}
	@GetMapping("/admin/voyage/new")
	public String createvoyageform(Model model) {
		Voyage newvoyage =new Voyage();
		model.addAttribute("newvoyage", newvoyage);
		System.out.println(1);
		return "createVoyageForm";
	}
	
	@PostMapping("/admin/voyage/new")
	public String savevoyage(@ModelAttribute("newvoyage") Voyage newvoyage) {
		voyagedao.insert(newvoyage);
		return "redirect:/admin/voyage";
	}
	
	@GetMapping("/admin/voyage/edit/{id}")
	public String editcrewform(@PathVariable int id, Model model) {
		model.addAttribute("voyage", voyagedao.getById(id));
		return "editVoyageForm";
	}
	
	@PostMapping("/admin/voyage/edit/{id}")
	public String updateStudent(@PathVariable int id,
			@ModelAttribute("voyage") Voyage voyage,
			Model model) {
		voyagedao.update(voyage, id);
		return "redirect:/admin/voyage";
	}
	
	@GetMapping("/admin/voyage/delete/{id}")
	public String deletevoyage(@PathVariable int id) {
		voyagedao.delete(id);
		return "redirect:/admin/voyage";
	}
	
}