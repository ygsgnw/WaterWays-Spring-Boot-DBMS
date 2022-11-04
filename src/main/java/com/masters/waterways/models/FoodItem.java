package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {

	private int FoodItemId;
	private int VoyageId;
	private int FoodCost;
	private String FoodName;
	private String FoodDescription;

	public int getFoodItemId() {
		return FoodItemId;
	}

	public void setFoodItemId(int foodItemId) {
		FoodItemId = foodItemId;
	}

	public int getVoyageId() {
		return VoyageId;
	}

	public void setVoyageId(int voyageId) {
		VoyageId = voyageId;
	}

	public String getFoodName() {
		return FoodName;
	}

	public void setFoodName(String foodName) {
		FoodName = foodName;
	}

	public int getFoodCost() {
		return FoodCost;
	}

	public void setFoodCost(int foodCost) {
		FoodCost = foodCost;
	}

	public String getFoodDescription() {
		return FoodDescription;
	}

	public void setFoodDescription(String foodDescription) {
		FoodDescription = foodDescription;
	}
}
