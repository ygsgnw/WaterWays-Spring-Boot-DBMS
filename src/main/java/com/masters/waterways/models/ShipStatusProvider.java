package com.masters.waterways.models;

import java.util.Map;

public class ShipStatusProvider {
    public static final Map<Integer, String> getShipStatusDesc = Map.of(
            1, "OPERATIONAL",
            2, "SUSPENDED",
            3, "MAINTENANCE"
    );

    public static final Map<String, Integer> getShipStatusCode = Map.of(
            "OPERATIONAL", 1,
            "SUSPENDED", 2,
            "MAINTENANCE", 3
    );
}
