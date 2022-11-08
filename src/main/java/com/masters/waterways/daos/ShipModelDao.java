package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface ShipModelDao {
    int insert (ShipModel shipmodel);
	List<ShipModel> getAll ();
	ShipModel getById (int id);
}
