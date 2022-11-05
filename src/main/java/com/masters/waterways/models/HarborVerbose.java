package com.masters.waterways.models;

import java.util.ArrayList;
import java.util.List;

public class HarborVerbose {
    private Harbor harbor;
    private String harborStatusDesc;

    public Harbor getHarbor() {
        return harbor;
    }

    public void setHarbor(Harbor harbor) {
        this.harbor = harbor;
    }

    public String getHarborStatusDesc() {
        return harborStatusDesc;
    }

    public void setHarborStatusDesc(String harborStatusDesc) {
        this.harborStatusDesc = harborStatusDesc;
    }

    public List<HarborVerbose> transform(List<Harbor> harborList) {
        List<HarborVerbose> harborVerboseList = new ArrayList<>();
        for (Harbor harbor: harborList) {
            HarborVerbose harborVerbose = new HarborVerbose();
            harborVerbose.setHarbor(harbor);
            harborVerbose.setHarborStatusDesc(HarborStatusProvider.getHarborStatusDesc.get(harbor.getHarborStatusCode()));
            harborVerboseList.add(harborVerbose);
        }
        return harborVerboseList;
    }
}
