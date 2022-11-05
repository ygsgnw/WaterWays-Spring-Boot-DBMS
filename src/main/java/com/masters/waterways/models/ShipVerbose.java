package com.masters.waterways.models;

import java.util.ArrayList;
import java.util.List;

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

    public List<ShipVerbose> transform(List<Ship> shipList) {
        List<ShipVerbose> shipVerboseList = new ArrayList<>();
        for (Ship ship: shipList) {
            ShipVerbose shipVerbose = new ShipVerbose();
            shipVerbose.setShip(ship);
            shipVerbose.setShipStatusDesc(ShipStatusProvider.getShipStatusDesc.get(ship.getShipStatusCode()));
            shipVerboseList.add(shipVerbose);
        }
        return shipVerboseList;
    }
}
