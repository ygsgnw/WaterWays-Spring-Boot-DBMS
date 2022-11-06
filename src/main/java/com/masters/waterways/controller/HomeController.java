package com.masters.waterways.controller;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.*;
import com.masters.waterways.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

import com.masters.waterways.models.VoyageStatusProvider;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private VoyageDao voyageDao;

	@Autowired
	private HarborDao harborDao;

	@Autowired
	private RoomBookingDao roomBookingDao;

	@Autowired
	FoodBookingDao foodBookingDao;

	@Autowired
	RoomBookingDetailsDao roomBookingDetailsDao;

	@Autowired
	FoodItemDao foodItemDao;

	@GetMapping("/")
	public String home(){
		return "Home";
	}

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

		List<Voyage> voyages = voyageDao.getAllActive();

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

		List<VoyageVerbose> voyageVerboseList = VoyageVerbose.transform(voyages);
		
		model.addAttribute("upcoming_voyages", voyageVerboseList);
		model.addAttribute("harbors", harborDao.getAll());

		return "VoyageListHomeAndUser";
	}


	@GetMapping("/voyages/{id}")
	public String voyagesDetails (@PathVariable("id") int voyageId, Model model, HttpSession session) {

		List<Voyage> voyage = new ArrayList<>();
		voyage.add(voyageDao.getById(voyageId));

		List<VoyageVerbose> voyageVerbose = VoyageVerbose.transform(voyage);
		model.addAttribute("voyageverboselist", voyageVerbose);

		boolean signedIn = false;

		if (authenticationService.isAuthenticated(session)) {
			signedIn=true;
			model.addAttribute("room_booking_details_with_foods", roomBookingDetailsDao.getAllByUserIdAndVoyageId(authenticationService.getCurrentUser(session), voyageId));
			model.addAttribute("fooditems", foodItemDao.getAllByVoyageId(voyageId));
		}

		model.addAttribute("signedIn", signedIn );

		return "VoyageDetailsUserAndHome";
	}




}
