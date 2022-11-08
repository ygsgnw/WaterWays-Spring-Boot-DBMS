package com.masters.waterways.controller;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.*;
import com.masters.waterways.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminVoyageController {

	@Autowired
	private VoyageDao voyagedao;

	@Autowired
	VoyageDao voyageDao;

	@Autowired
	VoyageUserViewDao voyageUserViewDao;

	@Autowired
	HarborDao harborDao;

	@Autowired
	VoyageVerboseTransform voyageVerboseTransform;

	@Autowired
	ShipDao shipDao;

	@Autowired
	AuthenticationService authenticationService;

	@GetMapping("/admin/voyages")
	public String voyagesList (Model model,
							   @RequestParam(name="fromid", required = false) Integer from_harbour_id,
							   @RequestParam(name="toid", required = false) Integer to_harbour_id,
							   @RequestParam(name="fromdate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime depart_after_datetime,
							   @RequestParam(name="todate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrive_before_datetime
			, HttpSession session) {

		if (!authenticationService.isAdmin(session))
			return "redirect:/login";

		System.out.println("hello");
		System.out.println(from_harbour_id);
		System.out.println(to_harbour_id);
		System.out.println(depart_after_datetime);
		System.out.println(arrive_before_datetime);

		List<VoyageUserView> voyages = voyageUserViewDao.getAll();

		for (VoyageUserView v: voyages)
			System.out.println(v.getArrivalHarborId());

		if (from_harbour_id != null && from_harbour_id != -1) {
			List<VoyageUserView> new_voyages = new ArrayList<>();
			for (VoyageUserView v: voyages)
				if (from_harbour_id.equals(v.getDepartureHarborId()))
					new_voyages.add(v);
			voyages = new_voyages;
		}

		if (to_harbour_id != null && to_harbour_id != -1) {
			List<VoyageUserView> new_voyages = new ArrayList<>();
			for (VoyageUserView v: voyages)
				if (to_harbour_id.equals(v.getArrivalHarborId()))
					new_voyages.add(v);
			voyages = new_voyages;
		}

		if (depart_after_datetime != null) {
			List<VoyageUserView> new_voyages = new ArrayList<>();
			for (VoyageUserView v: voyages)
				if (v.getDepartureTime().isAfter(depart_after_datetime))
					new_voyages.add(v);
			voyages = new_voyages;
		}

		if (arrive_before_datetime != null) {
			List<VoyageUserView> new_voyages = new ArrayList<>();
			for (VoyageUserView v: voyages)
				if (v.getArrivalTime().isBefore(arrive_before_datetime))
					new_voyages.add(v);
			voyages = new_voyages;
		}

		model.addAttribute("voyages", voyages);
		model.addAttribute("harbors", harborDao.getAll());
		model.addAttribute("voyageStatuses", VoyageStatusProvider.getVoyageStatusDesc);

		return "VoyageListAdmin";
	}


	@GetMapping("/admin/voyage/add")
	public String createvoyageform(Model model, HttpSession session) {

		if (!authenticationService.isAdmin(session))
			return "redirect:/login";

		Voyage newvoyage = new Voyage();
		model.addAttribute("newvoyage", newvoyage);
		model.addAttribute("shipList", shipDao.getAll());
		model.addAttribute("harborList", harborDao.getAll());
		model.addAttribute("voyageStatuses", VoyageStatusProvider.getVoyageStatusCode);
		return "AddVoyageAdminForm";
	}
	
	@PostMapping("/admin/voyage/add")
	public String savevoyage(@ModelAttribute("newvoyage") Voyage newvoyage, HttpSession session) {

		if (!authenticationService.isAdmin(session))
			return "redirect:/login";

		voyagedao.insert(newvoyage);
		return "redirect:/admin/voyages";
	}

	@GetMapping("/admin/voyage/{voyageId}")
	public String voyageDetailsAdmin(@PathVariable int voyageId, Model model, HttpSession session) {

		if (!authenticationService.isAdmin(session))
			return "redirect:/login";

		boolean isCompleted = voyageDao.isVoyageCompletedByVoyageId(voyageId);

		System.out.println(isCompleted);

		model.addAttribute("voyageForFare", voyageDao.getById(voyageId));

		VoyageUserView voyage =voyageUserViewDao.getById(voyageId);
		model.addAttribute("voyage", voyage);
		model.addAttribute("voyageStatuses", VoyageStatusProvider.getVoyageStatusDesc);

		model.addAttribute("voyageId", voyageId);
		model.addAttribute("isCompleted", isCompleted);
		return "VoyageDetailsAdmin";
	}


	@GetMapping("/admin/voyage/{id}/updatestatus")
	public String updateVoyageStatus(@PathVariable int id, Model model , HttpSession session) {

		if (!authenticationService.isAdmin(session))
			return "redirect:/login";


		if (VoyageStatusProvider.getVoyageStatusDesc.get(voyageDao.getById(id).getVoyageStatusCode()).equals("SUSPENDED")){
			voyageDao.setOperational(id);
		}
		else {
			voyageDao.setSuspended(id);
		}
		return "redirect:/admin/voyage/{id}";
	}


	@PostMapping("/admin/voyage/{voyageId}/updatefare")
	public String updateVoyageForm(@ModelAttribute Voyage voyageForFare, @PathVariable int voyageId,Model model, HttpSession session) {

		if (!authenticationService.isAdmin(session))
			return "redirect:/login";


		System.out.println(voyageForFare.getFare());
		voyageDao.updateVoyageByFare(voyageId, voyageForFare.getFare());
		return "redirect:/admin/voyage/{voyageId}";
	}
//
//	@PostMapping("/admin/voyage/edit/{id}")
//	public String update(@PathVariable("id") int voyageId,
//			@ModelAttribute("fare") int fare,
//			Model model) {
//		voyagedao.updateVoyageByFare(voyageId, fare);
//		return "redirect:/admin/voyage";
//	}

	
}