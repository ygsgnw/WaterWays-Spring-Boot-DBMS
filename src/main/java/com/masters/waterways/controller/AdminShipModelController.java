package com.masters.waterways.controller;


import com.masters.waterways.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.masters.waterways.daos.ShipModelDao;
import com.masters.waterways.models.ShipModel;

import javax.servlet.http.HttpSession;

@Controller
public class AdminShipModelController {
	@Autowired
	private ShipModelDao shipModelDao;

	@Autowired
	AuthenticationService authenticationService;

	@GetMapping("/admin/shipmodel")
	public String listshipmodel(Model model, HttpSession session) {

		if (!authenticationService.isAdmin(session))
			return "redirect:/login";

		model.addAttribute("shipmodels", shipModelDao.getAll());
		return "ShipModelList";
	}
	@GetMapping("/admin/shipmodel/add")
	public String createshipmodelform(Model model, HttpSession session) {

		if (!authenticationService.isAdmin(session))
			return "redirect:/login";

		ShipModel newshipmodel =new ShipModel();
		model.addAttribute("newshipmodel", newshipmodel);
		return "AddShipModelForm";
	}
	
	@PostMapping("/admin/shipmodel/add")
	public String saveshimodel(@ModelAttribute("newshipmodel") ShipModel newshipmodel, HttpSession session) {

		if (!authenticationService.isAdmin(session))
			return "redirect:/login";

		shipModelDao.insert(newshipmodel);
		return "redirect:/admin/shipmodel";
	}
	
//	@GetMapping("/admin/shipmodel/{id}/update")
//	public String editshipmodelform(@PathVariable("id") int shipModelId, Model model) {
//		model.addAttribute("shipmodel", shipModelDao.getById(shipModelId));
//		return "UpdateShipModelForm";
//	}
	
//	@PostMapping("/admin/shipmodel/{id}/update")
//	public String updateshipmodel(@PathVariable int id,
//			@ModelAttribute("shipmodel") ShipModel shipmodel,
//			Model model) {
//
//		shipModelDao.update(shipmodel, id);
//		return "redirect:/admin/shipmodel";
//	}
	
}
