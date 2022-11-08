package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface ShipDao {
    int insert (Ship ship);

	List<Ship> getAll ();

    List<Ship> getAllOperational();

    Ship getById (int id);

    void setOperational(int id);

    void setSuspended(int id);
}
