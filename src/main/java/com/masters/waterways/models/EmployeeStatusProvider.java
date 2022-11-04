package com.masters.waterways.models;

import java.util.Map;

public class EmployeeStatusProvider {
    public static final Map<Integer, String> getEmployeeStatusDesc = Map.of(
            1, "ACTIVE",
            2, "SUSPENDED"
    );

    public static final Map<String, Integer> getEmployeeStatusCode = Map.of(
            "ACTIVE", 1,
            "SUSPENDED", 2
    );
}
