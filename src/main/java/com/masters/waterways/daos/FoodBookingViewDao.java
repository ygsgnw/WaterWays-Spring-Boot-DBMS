package com.masters.waterways.daos;

import com.masters.waterways.models.FoodBookingView;

import java.util.List;

public interface FoodBookingViewDao {
    List<FoodBookingView> getAll();

    List<FoodBookingView> getAllByVoyageId(int voyageId);

    List<FoodBookingView> getAllByRoomIdAndVoyageId(int voyageId, int roomId);

    FoodBookingView getById(int id);

}
