package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface shipdao {
	
    int save(Ship ship);
	
	int update(Ship ship,long id);
	
	int delete(long id);
	
	List<Ship> getall();
	
	Ship getbyid(long id);
}
