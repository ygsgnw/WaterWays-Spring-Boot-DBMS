package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface passengerdao {
	
    int save(Passenger passenger);
	
	int update(Passenger passenger,long id);
	
	int delete(long id);
	
	List<Passenger> getall();
	
	Passenger getbyid(long id);
}
