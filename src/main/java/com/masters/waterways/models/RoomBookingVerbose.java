package com.masters.waterways.models;

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
}
