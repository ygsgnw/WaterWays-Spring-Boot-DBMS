package com.masters.waterways.models;

import java.util.Map;

public class HarborStatusProvider {
    public static final Map<Integer, String> getHarborStatusDesc = Map.of(
            1, "OPERATIONAL",
            2, "MAINTENANCE",
            3, "SUSPENDED"
    );

    public static final Map<String, Integer> getHarborStatusCode = Map.of(
            "OPERATIONAL", 1,
            "MAINTENANCE", 2,
            "SUSPENDED", 3
    );
}
