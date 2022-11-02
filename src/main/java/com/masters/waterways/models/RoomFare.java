package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomFare {
    private int VoyageId;
    private int Fare;

    public int getVoyageId() {
        return VoyageId;
    }

    public void setVoyageId(int voyageId) {
        VoyageId = voyageId;
    }

    public int getFare() {
        return Fare;
    }

    public void setFare(int fare) {
        Fare = fare;
    }
}
