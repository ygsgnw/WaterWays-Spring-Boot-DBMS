package com.masters.waterways.services;

import com.masters.waterways.daos.HarborDao;
import com.masters.waterways.daos.VoyageVerboseTransform;
import com.masters.waterways.models.Harbor;
import com.masters.waterways.models.Voyage;
import com.masters.waterways.models.VoyageStatusProvider;
import com.masters.waterways.models.VoyageVerbose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VoyageVerboseTransformImpl implements VoyageVerboseTransform {

    @Autowired
    HarborDao harborDao;

    @Override
    public List<VoyageVerbose> transform(List<Voyage> voyageList) {
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
