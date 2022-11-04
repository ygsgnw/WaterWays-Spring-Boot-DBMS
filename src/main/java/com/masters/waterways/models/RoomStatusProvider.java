package com.masters.waterways.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomStatusProvider {
    public static final Map<Integer, String> getRoomStatusDesc = Map.of(
            1, "AVAILABLE",
            2, "BOOKED",
            3, "RESERVED",
            4, "MAINTENANCE"
    );

    public static final Map<String, Integer> getRoomStatusCode = Map.of(
            "AVAILABLE", 1,
            "BOOKED", 2,
            "RESERVED", 3,
            "MAINTENANCE", 4
    );

    public List<RoomBookingVerbose> transform(List<RoomBooking> roomBookingList){
        List<RoomBookingVerbose> roomBookingVerboseList = new ArrayList<>();
        for (RoomBooking roomBooking: roomBookingList) {
            RoomBookingVerbose roomBookingVerbose = new RoomBookingVerbose();
            roomBookingVerbose.setRoomBooking(roomBooking);
            roomBookingVerbose.setRoomBookingStatusDesc(getRoomStatusDesc.get(roomBooking.getRoomStatusCode()));
            roomBookingVerboseList.add(roomBookingVerbose);
        }
        return roomBookingVerboseList;
    }
}
