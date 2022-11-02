package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;

public interface FoodBookingDao {
	
    int save(FoodBooking fb);
	
	int update(FoodBooking fb,int id);
	
	int delete(int id);
	
	List<FoodBooking> getall();
	
	FoodBooking getbyid(int id);

	void bookFood(int userId, int voyageId, int roomId, int foodItemId, int foodCount);
}
