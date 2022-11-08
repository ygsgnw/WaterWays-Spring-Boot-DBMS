package com.masters.waterways.daos;

import com.masters.waterways.models.FoodBookingAdminView;

import java.util.List;

public interface FoodBookingAdminViewDao {
    List<FoodBookingAdminView> getAll();

    List<FoodBookingAdminView> getAllByVoyageId(int voyageId);

    List<FoodBookingAdminView> getAllByUserIdAndRoomIdAndVoyageId(int userId, int voyageId, int roomId);

    FoodBookingAdminView getById(int id);
}
