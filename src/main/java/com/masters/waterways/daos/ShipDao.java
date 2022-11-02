package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface ShipDao {
	
    int save(Ship ship);
	
	int update(Ship ship,int id);
	
	int delete(int id);
	
	List<Ship> getall();
	
	Ship getbyid(int id);
}
