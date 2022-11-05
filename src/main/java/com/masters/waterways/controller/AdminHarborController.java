package com.masters.waterways.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.masters.waterways.daos.HarborDao;
import com.masters.waterways.models.Harbor;

@Controller
public class AdminHarborController {
	@Autowired
	private HarborDao hardao;
	
	@GetMapping("/admin/harbor")
	public String listharbors(Model model) {
		model.addAttribute("harbors", hardao.getAll());
		return "HarborList";
	}
	@GetMapping("/admin/harbor/new")
	public String createharborform(Model model) {
		Harbor newharbor =new Harbor();
		model.addAttribute("newharbor", newharbor);
		return "createHarborForm";
	}
	
	@PostMapping("/admin/harbor/new")
	public String savecrew(@ModelAttribute("newcrew") Harbor newharbor) {
		hardao.insert(newharbor);
		return "redirect:/admin/harbor";
	}
	
	@GetMapping("/admin/harbor/edit/{id}")
	public String editharborform(@PathVariable int id, Model model) {
		model.addAttribute("Harbor", hardao.getById(id));
		return "editHarborForm";
	}
	
	@PostMapping("/admin/harbor/edit/{id}")
	public String updateharbor(@PathVariable int id,
			@ModelAttribute("harbor") Harbor harbor,
			Model model) {
		hardao.update(harbor, id);
		return "redirect:/admin/harbor";
	}
	
	@GetMapping("/admin/harbor/delete/{id}")
	public String deleteharbor(@PathVariable int id) {
		hardao.delete(id);
		return "redirect:/admin/harbor";
	}
}
