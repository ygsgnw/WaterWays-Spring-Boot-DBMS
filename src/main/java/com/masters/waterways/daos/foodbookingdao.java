package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;

public interface foodbookingdao {
	
    int save(FoodBooking fb);
	
	int update(FoodBooking fb,long id);
	
	int delete(long id);
	
	List<FoodBooking> getall();
	
	FoodBooking getbyid(long id);
}
