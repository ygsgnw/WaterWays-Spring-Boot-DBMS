package com.masters.waterways.models;

import com.masters.waterways.daos.FoodItemDao;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodBookingVerbose {
    private FoodBooking f;
    private String foodName, foodDescription;

    public FoodBooking getFoodBooking() {
        return f;
    }

    public void setFoodBooking(FoodBooking f) {
        this.f = f;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    static FoodItemDao foodItemDao;

    static public List<FoodBookingVerbose> transform(List<FoodBooking> foodBookingList) {

        List<FoodItem> foodItemList = foodItemDao.getAll();

        Map<Pair<Integer, Integer>, Pair<String, String>> getFoodNameAndDesc = new HashMap<>();

        for (FoodItem foodItem: foodItemList)
            getFoodNameAndDesc.put(Pair.of(foodItem.getVoyageId(), foodItem.getFoodItemId()), Pair.of(foodItem.getFoodName(), foodItem.getFoodDescription()));

        List<FoodBookingVerbose> foodBookingVerboseList = new ArrayList<>();
        for (FoodBooking f: foodBookingList) {
            FoodBookingVerbose fv = new FoodBookingVerbose();
            fv.setFoodBooking(f);
            fv.setFoodName(getFoodNameAndDesc.get(Pair.of(f.getVoyageId(), f.getFoodItemId())).getFirst());
            fv.setFoodDescription(getFoodNameAndDesc.get(Pair.of(f.getVoyageId(), f.getFoodItemId())).getSecond());
            foodBookingVerboseList.add(fv);
        }
        return foodBookingVerboseList;
    }
}
