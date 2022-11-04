package com.masters.waterways.daos;

import java.util.List;

import com.masters.waterways.models.FoodItem;

public interface FoodItemDao {
    int insert (FoodItem fi);
	void delete(FoodItem foodItem);

	List<FoodItem> getAll ();
	FoodItem getById (int id);
	List<FoodItem> getFoodItemsByVoyageId (int voyageId);
}
