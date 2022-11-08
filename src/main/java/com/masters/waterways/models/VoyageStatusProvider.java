package com.masters.waterways.models;

import java.util.Map;

public class VoyageStatusProvider {
    public static final Map<Integer, String> getVoyageStatusDesc = Map.of(
            1, "OPERATIONAL",
            2, "SUSPENDED"
    );

    public static final Map<String, Integer> getVoyageStatusCode = Map.of(
            "OPERATIONAL", 1,
            "SUSPENDED", 2
    );

}
