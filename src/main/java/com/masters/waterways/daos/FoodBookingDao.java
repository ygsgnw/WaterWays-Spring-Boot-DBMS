package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;

public interface FoodBookingDao {
<<<<<<< HEAD
	
    int save(FoodBooking fb);
	
	int update(FoodBooking fb,int id);
	
	int delete(int id);
	
	List<FoodBooking> getall();
	
	FoodBooking getbyid(int id);

	void bookFood(int userId, int voyageId, int roomId, int foodItemId, int foodCount);
=======
    int insert (FoodBooking fb);
	int update (FoodBooking fb,int id);
	int delete (int id);
	List<FoodBooking> getAll ();
	FoodBooking getById (int id);
>>>>>>> 2f755b16371c6dbfff0525b7e27e6a15f162674d
}
