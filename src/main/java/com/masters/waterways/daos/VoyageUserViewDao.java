package com.masters.waterways.daos;

import com.masters.waterways.models.VoyageUserView;

import java.util.List;

public interface VoyageUserViewDao {
    List<VoyageUserView> getAll();

    VoyageUserView getById(int id);

    List<VoyageUserView> getAllActive();

    List<VoyageUserView> getAllActiveByUserId(int userId);

    List<VoyageUserView> getAllCompletedByUserId(int userId);
}
