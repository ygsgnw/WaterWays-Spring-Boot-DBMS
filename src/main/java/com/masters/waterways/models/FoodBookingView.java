package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodBookingView {
    private String UserName;
    private int TransactionId;
    private int RoomId;
    private int VoyageId;
    private String FoodName;
    private String FoodDescription;
    private int FoodItemCount;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }

    public int getVoyageId() {
        return VoyageId;
    }

    public void setVoyageId(int voyageId) {
        VoyageId = voyageId;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodDescription() {
        return FoodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        FoodDescription = foodDescription;
    }

    public int getFoodItemCount() {
        return FoodItemCount;
    }

    public void setFoodItemCount(int foodItemCount) {
        FoodItemCount = foodItemCount;
    }

    public int getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(int transactionId) {
        TransactionId = transactionId;
    }
}
