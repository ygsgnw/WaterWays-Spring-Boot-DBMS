package com.masters.waterways.daos;

import com.masters.waterways.models.VoyageUserView;

import java.util.List;

public interface VoyageUserViewDao {
    VoyageUserView getById(int id);

    List<VoyageUserView> getAll();

    List<VoyageUserView> getAllFuture();

    List<VoyageUserView> getAllFutureByUserId(int userId);

    List<VoyageUserView> getAllCompletedByUserId(int userId);
}
