package com.masters.waterways.daos;

import java.util.List;

import com.masters.waterways.models.FoodItem;

public interface fooditemdao {
	
    int save(FoodItem fi);
	
	int update(FoodItem fi,int id);
	
	int delete(int id);
	
	List<FoodItem> getall();
	
	FoodItem getbyid(int id);
}
