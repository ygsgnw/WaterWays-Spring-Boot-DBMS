package com.masters.waterways.models;

import com.masters.waterways.daos.HarborDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoyageStatusProvider {
    public static final Map<Integer, String> getVoyageStatusDesc = Map.of(
            1, "OPERATIONAL",
            2, "COMPLETED",
            3, "CANCELLED"
    );

    public static final Map<String, Integer> getVoyageStatusCode = Map.of(
            "OPERATIONAL", 1,
            "COMPLETED", 2,
            "CANCELLED", 3
    );

}
