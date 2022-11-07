package com.masters.waterways.models;

import com.masters.waterways.daos.HarborDao;
import org.springframework.beans.factory.annotation.Autowired;

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
}
