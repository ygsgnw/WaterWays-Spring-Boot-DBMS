package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;

public interface FoodBookingDao {

    int insert (FoodBooking fb);
	int update (FoodBooking fb,int id);
	int delete (int id);
	List<FoodBooking> getAll ();
	FoodBooking getById (int id);
    void bookFood(int userId, int voyageId, int roomId, int foodItemId, int foodCount);
}
