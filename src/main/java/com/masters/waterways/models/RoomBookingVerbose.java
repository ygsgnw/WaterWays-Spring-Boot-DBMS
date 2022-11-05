package com.masters.waterways.models;

import java.util.ArrayList;
import java.util.List;

public class RoomBookingVerbose {
    private RoomBooking roomBooking;
    private String roomBookingStatusDesc;

    public RoomBooking getRoomBooking() {
        return roomBooking;
    }

    public void setRoomBooking(RoomBooking roomBooking) {
        this.roomBooking = roomBooking;
    }

    public String getRoomBookingStatusDesc() {
        return roomBookingStatusDesc;
    }

    public void setRoomBookingStatusDesc(String roomBookingStatusDesc) {
        this.roomBookingStatusDesc = roomBookingStatusDesc;
    }

    public List<RoomBookingVerbose> transform(List<RoomBooking> roomBookingList){
        List<RoomBookingVerbose> roomBookingVerboseList = new ArrayList<>();
        for (RoomBooking roomBooking: roomBookingList) {
            RoomBookingVerbose roomBookingVerbose = new RoomBookingVerbose();
            roomBookingVerbose.setRoomBooking(roomBooking);
            roomBookingVerbose.setRoomBookingStatusDesc(RoomStatusProvider.getRoomStatusDesc.get(roomBooking.getRoomStatusCode()));
            roomBookingVerboseList.add(roomBookingVerbose);
        }
        return roomBookingVerboseList;
    }
}
