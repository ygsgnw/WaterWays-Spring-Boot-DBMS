package com.masters.waterways.models;

public class ShipStatus {
    private int ShipStatusCode;
    private String ShipStatusDesc;

    public int getShipStatusCode() {
        return ShipStatusCode;
    }

    public void setShipStatusCode(int shipStatusCode) {
        ShipStatusCode = shipStatusCode;
    }

    public String getShipStatusDesc() {
        return ShipStatusDesc;
    }

    public void setShipStatusDesc(String shipStatusDesc) {
        ShipStatusDesc = shipStatusDesc;
    }
}
