package com.masters.waterways.models;

import com.masters.waterways.daos.FoodBookingViewDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingDetails {
    private RoomBooking roomBooking;
    private List<FoodBookingView> foodBookingViewList;

    public RoomBooking getRoomBooking() {
        return roomBooking;
    }

    public void setRoomBooking(RoomBooking roomBooking) {
        this.roomBooking = roomBooking;
    }

    public List<FoodBookingView> getFoodBookingViewList() {
        return foodBookingViewList;
    }

    public void setFoodBookingViewList(List<FoodBookingView> foodBookingViewList) {
        this.foodBookingViewList = foodBookingViewList;
    }
}
