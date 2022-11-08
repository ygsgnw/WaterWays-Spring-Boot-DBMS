package com.masters.waterways.controller;

import com.masters.waterways.daos.VoyageDao;
import com.masters.waterways.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.masters.waterways.daos.CrewDao;
import com.masters.waterways.models.Crew;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminCrewController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private CrewDao crewDao;

	@Autowired
	private VoyageDao voyageDao;

	@GetMapping("/admin/voyage/{voyageId}/crew")
	public String listCrew(@PathVariable int voyageId, Model model) {

//		if (authenticationService.isAdmin(session))

		System.out.println(voyageId);
		boolean isCompleted = voyageDao.isVoyageCompletedByVoyageId(voyageId);
		model.addAttribute("voyageId", voyageId);
		model.addAttribute("isCompleted", isCompleted);
		model.addAttribute("crewListByVoyageId", crewDao.getAllByVoyageId(voyageId));

		return "VoyageCrewList";
	}
	@GetMapping("/admin/voyage/{voyageId}/crew/add")
	public String createcrewform(@PathVariable int voyageId, Model model) {

		Crew new_crew = new Crew();
		new_crew.setVoyageId(voyageId);

		model.addAttribute("new_crew", new_crew);

//		List<String> crew_roles=new ArrayList<String>();
//		crew_roles.add("role1");
//		crew_roles.add("role2");
//		crew_roles.add("role3");

//		model.addAttribute("crew_roles", crew_roles);
		model.addAttribute("not_crew_employees", crewDao.getAllAvailableEmployees(voyageId));
		return "AddCrewForm";
	}
	
	@PostMapping("/admin/voyage/{voyageId}/crew/add")
	public String insertCrew(@ModelAttribute("new_crew") Crew new_crew) {
		crewDao.insert(new_crew);
		return "redirect:/admin/voyage/{voyageId}/crew";
	}
	
	@GetMapping("/admin/voyage/{voyageId}/crew/update/{employeeId}")
	public String editcrewform(@PathVariable int voyageId, @PathVariable int employeeId, Model model) {

		Crew crew = new Crew();
		crew.setVoyageId(voyageId);
		crew.setEmployeeId(employeeId);

		model.addAttribute("crew", crew);

//		List<String> crew_roles=new ArrayList<>();
//		crew_roles.add("role1");
//		crew_roles.add("role2");
//		crew_roles.add("role3");
//		model.addAttribute("crew_roles", crew_roles);

		return "UpdateCrewForm";
	}
	
	@PostMapping("/admin/voyage/{voyageId}/crew/update/{employeeId}")
	public String updateRole(@ModelAttribute("crew") Crew crew, Model model) {
		crewDao.updateRole(crew);
		return "redirect:/admin/voyage/{voyageId}/crew";
	}
	
	@GetMapping("/admin/voyage/{voyageId}/crew/delete/{employeeId}")
	public String deleteCrew(@PathVariable int voyageId, @PathVariable int employeeId) {

		Crew crew = new Crew();
		crew.setVoyageId(voyageId);
		crew.setEmployeeId(employeeId);

		crewDao.delete(crew);
		return "redirect:/admin/voyage/{voyageId}/crew";
	}
	
}
