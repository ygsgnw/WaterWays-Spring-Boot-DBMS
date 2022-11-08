package com.masters.waterways.daos;

import java.util.List;

import com.masters.waterways.models.FoodItem;

public interface FoodItemDao {
    int insert (FoodItem foodItem);

	List<FoodItem> getAll ();

	List<FoodItem> getAllByVoyageId(int voyageId);

	FoodItem getById (int voyageId, int foodItemId);
}
