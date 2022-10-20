package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;

public interface foodtypedao {
	
    int save(FoodType foodtype);
	
	int update(FoodType foodtype,long id);
	
	int delete(long id);
	
	List<FoodType> getall();
	
	FoodType getbyid(long id);
}
