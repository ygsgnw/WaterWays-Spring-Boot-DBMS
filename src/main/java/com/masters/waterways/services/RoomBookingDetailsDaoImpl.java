package com.masters.waterways.services;

import com.masters.waterways.daos.FoodBookingDao;
import com.masters.waterways.daos.FoodBookingViewDao;
import com.masters.waterways.daos.RoomBookingDao;
import com.masters.waterways.daos.RoomBookingDetailsDao;
import com.masters.waterways.models.RoomBooking;
import com.masters.waterways.models.RoomBookingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomBookingDetailsDaoImpl implements RoomBookingDetailsDao {

    @Autowired
    RoomBookingDao roomBookingDao;

    @Autowired
    FoodBookingViewDao foodBookingViewDao;

    @Override
    public List<RoomBookingDetails> getAllByUserIdAndVoyageId(int userId, int voyageId) {

        List<RoomBookingDetails> roomBookingDetailsList = new ArrayList<>();

        List<RoomBooking> roomBookingList = roomBookingDao.getAllByUserIdAndVoyageId(userId, voyageId);

        for (RoomBooking roomBooking: roomBookingList) {
            RoomBookingDetails roomBookingDetails = new RoomBookingDetails();
            roomBookingDetails.setRoomBooking(roomBooking);
            roomBookingDetails.setFoodBookingViewList(foodBookingViewDao.getAllByRoomIdAndVoyageId(voyageId, roomBooking.getRoomId()));
            roomBookingDetailsList.add(
                    roomBookingDetails
            );
        }

        return roomBookingDetailsList;
    }
}
