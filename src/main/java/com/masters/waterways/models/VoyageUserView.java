package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoyageUserView {
    private int VoyageId;
    private int Fare;
    private int ShipSerialId;
    private String ModelName;
    private String DepartureHarborName;
    private String ArrivalHarborName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime DepartureTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime ArrivalTime;
    private int VoyageStatusCode;

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

    public int getShipSerialId() {
        return ShipSerialId;
    }

    public void setShipSerialId(int shipSerialId) {
        ShipSerialId = shipSerialId;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    public String getDepartureHarborName() {
        return DepartureHarborName;
    }

    public void setDepartureHarborName(String departureHarborName) {
        DepartureHarborName = departureHarborName;
    }

    public String getArrivalHarborName() {
        return ArrivalHarborName;
    }

    public void setArrivalHarborName(String arrivalHarborName) {
        ArrivalHarborName = arrivalHarborName;
    }

    public LocalDateTime getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        DepartureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public int getVoyageStatusCode() {
        return VoyageStatusCode;
    }

    public void setVoyageStatusCode(int voyageStatusCode) {
        VoyageStatusCode = voyageStatusCode;
    }
}
