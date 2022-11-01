package com.masters.waterways.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masters.waterways.daos.shipdao;
import com.masters.waterways.models.Ship;

@RestController
public class shipcontroller {
	@Autowired
	private shipdao ship;
	
	@GetMapping("/ships")
	public List<Ship> getallship() {
		return ship.getall();
	}
	
	@GetMapping("/ship/{id}")
	public Ship getshipid(@PathVariable int id) {
		return ship.getbyid(id);
	}
	@PostMapping("/ship")
	public int saveship(@RequestBody Ship sh) {
		return ship.save(sh);
	}
	
	
	
}
