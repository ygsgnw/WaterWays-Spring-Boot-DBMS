package com.masters.waterways.daos;

import com.masters.waterways.models.RoomBookingDetails;

import java.util.List;

public interface RoomBookingDetailsDao {
    List<RoomBookingDetails> getAllByUserIdAndVoyageId(int userId, int voyageId);
}
