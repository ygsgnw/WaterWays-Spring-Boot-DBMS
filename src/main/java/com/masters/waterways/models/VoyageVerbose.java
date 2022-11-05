package com.masters.waterways.models;

import com.masters.waterways.daos.HarborDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoyageVerbose {
    private Voyage voyage;
    private String DepartureHarborName;
    private String ArrivalHarborName;
    private String VoyageStatusDesc;

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
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

    public String getVoyageStatusDesc() {
        return VoyageStatusDesc;
    }

    public void setVoyageStatusDesc(String voyageStatusDesc) {
        VoyageStatusDesc = voyageStatusDesc;
    }

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
            voyageVerbose.setVoyageStatusDesc(VoyageStatusProvider.getVoyageStatusDesc.get(voyage.getVoyageStatusCode()));
            voyageVerbose.setArrivalHarborName(getHarborLocation.get(voyage.getArrivalHarborId()));
            voyageVerbose.setDepartureHarborName(getHarborLocation.get(voyage.getDepartureHarborId()));
            voyageVerboseList.add(voyageVerbose);
        }
        return voyageVerboseList;
    }
}
