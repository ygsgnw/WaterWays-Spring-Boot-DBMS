package com.masters.waterways.models;

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
}
