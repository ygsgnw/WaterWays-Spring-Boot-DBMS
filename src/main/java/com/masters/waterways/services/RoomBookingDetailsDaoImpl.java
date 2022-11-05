package com.masters.waterways.services;

import com.masters.waterways.daos.FoodBookingDao;
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
    FoodBookingDao foodBookingDao;

    @Override
    public List<RoomBookingDetails> getAllByUserIdAndVoyageId(int userId, int voyageId) {
        List<RoomBookingDetails> roomBookingDetailsList = new ArrayList<>();
        List<RoomBooking> roomBookingList = roomBookingDao.getAllByUserIdAndVoyageId(userId, voyageId);

        for (RoomBooking roomBooking: roomBookingList) {
            roomBookingDetailsList.add(
                    new RoomBookingDetails(roomBooking, foodBookingDao.getAllByUserIdAndRoomIdAndVoyageId(userId, voyageId, roomBooking.getRoomId()))
            );
        }

        return roomBookingDetailsList;
    }
}
