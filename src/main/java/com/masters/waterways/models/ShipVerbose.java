package com.masters.waterways.models;

public class ShipVerbose {
    private Ship ship;
    private String ShipStatusDesc;

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public String getShipStatusDesc() {
        return ShipStatusDesc;
    }

    public void setShipStatusDesc(String shipStatusDesc) {
        ShipStatusDesc = shipStatusDesc;
    }
}
