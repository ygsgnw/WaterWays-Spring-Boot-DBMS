package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface ShipDao {
    int insert (Ship ship);
	int update (Ship ship, int id);
	int delete (int id);
	List<Ship> getAll ();
	Ship getById (int id);
	List<ShipStatus> getAllShipStatuses ();
}
