package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;

public interface FoodBookingDao {
    void insert (FoodBooking foodBooking);
	List<FoodBooking> getAll ();
    List<FoodBooking> getAllByVoyageId (int voyageId);
    List<FoodBooking> getAllByUserIdAndRoomIdAndVoyageId (int userId, int voyageId, int roomId);
	FoodBooking getById (int id);
    void bookFood (int userId, int roomId, int foodCount, FoodItem foodItem);
}
