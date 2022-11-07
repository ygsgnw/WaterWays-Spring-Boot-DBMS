package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface ShipDao {
    int insert (Ship ship);
	int delete (int id);
	List<Ship> getAll ();
	Ship getById (int id);

    void setActive(int id);

	void setSuspended(int id);
}
