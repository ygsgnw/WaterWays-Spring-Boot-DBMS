package com.masters.waterways.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masters.waterways.daos.shipmodeldao;
import com.masters.waterways.models.ShipModel;

@RestController
public class shipmodelcontroller {
	@Autowired
	private shipmodeldao modeldao;
	
	@GetMapping("/shipmodel")
	public List<ShipModel> getallship() {
		return modeldao.getall();
	}
	
	@GetMapping("/shipmodel/{id}")
	public ShipModel getshipid(@PathVariable int id) {
		return modeldao.getbyid(id);
	}
	@PostMapping("/shipmodel")
	public int saveship(@RequestBody ShipModel sh) {
		return modeldao.save(sh);
	}
}
