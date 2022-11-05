package com.masters.waterways.models;

import java.util.List;

public class RoomBookingDetails {
    private RoomBooking roomBooking;
    private List<FoodBookingVerbose> foodBookingVerboseList;

    public RoomBookingDetails(RoomBooking roomBooking, List<FoodBooking> foodBookingList) {
        this.roomBooking = roomBooking;
        this.foodBookingVerboseList = FoodBookingVerbose.transform(foodBookingList);
    }

    public RoomBooking getRoomBooking() {
        return roomBooking;
    }

    public void setRoomBooking(RoomBooking roomBooking) {
        this.roomBooking = roomBooking;
    }

    public List<FoodBookingVerbose> getFoodBookingVerboseList() {
        return foodBookingVerboseList;
    }

    public void setFoodBookingVerboseList(List<FoodBookingVerbose> foodBookingVerboseList) {
        this.foodBookingVerboseList = foodBookingVerboseList;
    }
}
