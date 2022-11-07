package com.masters.waterways.daos;

import com.masters.waterways.models.Voyage;
import com.masters.waterways.models.VoyageVerbose;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface VoyageVerboseTransform {
    List<VoyageVerbose> transform(List<Voyage> voyageList);
}
