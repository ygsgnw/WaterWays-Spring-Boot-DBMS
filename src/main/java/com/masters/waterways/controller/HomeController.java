package com.masters.waterways.controller;

import com.masters.waterways.daos.HarborDao;
import com.masters.waterways.daos.UsersDao;
import com.masters.waterways.daos.VoyageDao;
import com.masters.waterways.models.Harbor;
import com.masters.waterways.models.Voyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.masters.waterways.models.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private VoyageDao voyageDao;

	@Autowired
	private HarborDao harborDao;
	
	@GetMapping("/")
	public String home(){
		return "Home";
	}

//	@GetMapping("/login")
//	public String login() {
//		return "Login";
//	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("newUser", new Users());
		return "Signup";
	}

	@PostMapping("/signup")
	public String signedup(@ModelAttribute("newUser") Users newUser) {
		usersDao.insert(newUser);
		return "redirect:/profile";
	}

	@GetMapping("/voyages")
	public String voyagesList (Model model,
			@RequestParam(name="fromid", required = false) Integer from_harbour_id,
			@RequestParam(name="toid", required = false) Integer to_harbour_id,
			@RequestParam(name="fromdate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime depart_after_datetime,
			@RequestParam(name="todate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrive_before_datetime
	) {

		System.out.println("hello");
		System.out.println(from_harbour_id);
		System.out.println(to_harbour_id);
		System.out.println(depart_after_datetime);
		System.out.println(arrive_before_datetime);

		List<Voyage> voyages = voyageDao.getAll();

		if (from_harbour_id != null) {
			List<Voyage> new_voyages = new ArrayList<>();
			for (Voyage v: voyages)
				if (v.getDepartureHarborId() == from_harbour_id)
					new_voyages.add(v);
			voyages = new_voyages;
		}

		if (to_harbour_id != null) {
			List<Voyage> new_voyages = new ArrayList<>();
			for (Voyage v: voyages)
				if (v.getArrivalHarborId() == to_harbour_id)
					new_voyages.add(v);
			voyages = new_voyages;
		}

		if (depart_after_datetime != null) {
			List<Voyage> new_voyages = new ArrayList<>();
			for (Voyage v: voyages)
				if (v.getDepartureTime().isAfter(depart_after_datetime))
					new_voyages.add(v);
			voyages = new_voyages;
		}

		if (arrive_before_datetime != null) {
			List<Voyage> new_voyages = new ArrayList<>();
			for (Voyage v: voyages)
				if (v.getArrivalTime().isBefore(arrive_before_datetime))
					new_voyages.add(v);
			voyages = new_voyages;
		}

		List<Harbor> harbors = harborDao.getAll();

		Dictionary<Integer, String> getHarbor = new Hashtable<>();

		for (Harbor h: harbors)
			getHarbor.put(h.getHarborId(), h.getLocation());

		class VoyageNeat {
			Voyage v;
			String DepartureHarbor, ArrivalHarbour;
		}

		List<VoyageNeat> voyagesNeat = new ArrayList<>();

		for (Voyage v: voyages) {
			VoyageNeat nv = new VoyageNeat();
			nv.DepartureHarbor = getHarbor.get(v.getDepartureHarborId());
			nv.ArrivalHarbour = getHarbor.get(v.getArrivalHarborId());
			nv.v = v;
			voyagesNeat.add(nv);
		}
		
		model.addAttribute("new_voyage", voyages);

		return "VoyageListHome";
	}

	@GetMapping("/voyages/{id}")
	public String voyagesDetails(@PathVariable int id, Model model) {
		model.addAttribute("voyage", voyageDao.getById(id));
		return "VoyageDetailsHome";
	}

	@GetMapping("/booking")
	public String booking(){
		return "redirect:/login";
	}


}
