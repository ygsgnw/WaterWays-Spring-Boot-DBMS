package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface voyagedao {
	
    int save(Voyage voyage);
	
	int update(Voyage voyage,long id);
	
	int delete(long id);
	
	List<Voyage> getall();
	
	Voyage getbyid(long id);
}
