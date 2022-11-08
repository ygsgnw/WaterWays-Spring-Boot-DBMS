package com.masters.waterways.controller;

import com.masters.waterways.daos.FoodItemDao;
import com.masters.waterways.daos.VoyageDao;
import com.masters.waterways.daos.VoyageUserViewDao;
import com.masters.waterways.models.Crew;
import com.masters.waterways.models.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminFoodItemController {

    @Autowired
    VoyageDao voyageDao;

    @Autowired
    FoodItemDao foodItemDao;

    @GetMapping("/admin/voyage/{voyageId}/fooditems")
    public String listFoodItems(@PathVariable int voyageId, Model model) {

        FoodItem foodItem = new FoodItem();
        foodItem.setVoyageId(voyageId);
        model.addAttribute("foodItem", foodItem);

        System.out.println(voyageId);

        boolean isCompleted = voyageDao.isVoyageCompletedByVoyageId(voyageId);

        model.addAttribute("voyageId", voyageId);
        model.addAttribute("isCompleted", isCompleted);
        model.addAttribute("foodItemListByVoyageId", foodItemDao.getAllByVoyageId(voyageId));

        return "VoyageFoodItemList";
    }

//    @GetMapping("/admin/voyage/{voyageId}/fooditems/add")
//    public String createFoodItemForm(@PathVariable int voyageId, Model model) {
//
//        FoodItem new_foodItem = new FoodItem();
//        new_foodItem.setVoyageId(voyageId);
//
//        model.addAttribute("new_foodItem", new_foodItem);
//
//        return "AddFoodItemForm";
//    }

    @PostMapping("/admin/voyage/{voyageId}/fooditems/add")
    public String insertFoodItem(@ModelAttribute("foodItem") FoodItem foodItem) {
        foodItemDao.insert(foodItem);
        System.out.println(foodItem.getFoodName());
        return "redirect:/admin/voyage/{voyageId}/fooditems";
    }

//    @GetMapping("/admin/voyage/{voyageId}/fooditem/update")
//    public String editcrewform(@PathVariable int voyageId, @PathVariable int employeeId, Model model) {
//
//        FoodItem foodItem = foodItemDao.g;
//        crew.setVoyageId(voyageId);
//        crew.setEmployeeId(employeeId);
//
//        model.addAttribute("crew", crew);
//
//        return "UpdateCrewForm";
//    }
//
//    @GetMapping("/admin/voyage/{voyageId}/fooditem/delete")
//    public String deleteCrew(@PathVariable int voyageId, @PathVariable int employeeId) {
//
//        Crew crew = new Crew();
//        crew.setVoyageId(voyageId);
//        crew.setEmployeeId(employeeId);
//
//        crewDao.delete(crew);
//        return "redirect:/admin/voyage/{voyageId}/crew";
//    }


}
