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

    static HarborDao harborDao;

    public static List<VoyageVerbose> transform(List<Voyage> voyageList) {

        List<Harbor> harbors = harborDao.getAll();
        Map<Integer, String> getHarborLocation = new HashMap<>();
        for (Harbor harbor: harbors)
            getHarborLocation.put(harbor.getHarborId(), harbor.getLocation());

        List<VoyageVerbose> voyageVerboseList = new ArrayList<>();
        for (Voyage voyage: voyageList) {
            VoyageVerbose voyageVerbose = new VoyageVerbose();
            voyageVerbose.setVoyage(voyage);
            voyageVerbose.setVoyageStatusDesc(getVoyageStatusDesc.get(voyage.getVoyageStatusCode()));
            voyageVerbose.setArrivalHarborName(getHarborLocation.get(voyage.getArrivalHarborId()));
            voyageVerbose.setDepartureHarborName(getHarborLocation.get(voyage.getDepartureHarborId()));
            voyageVerboseList.add(voyageVerbose);
        }
        return voyageVerboseList;
    }
}
