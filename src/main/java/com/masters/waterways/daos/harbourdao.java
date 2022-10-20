package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface harbourdao {
	
    int save(Harbor harbor);
	
	int update(Harbor harbor,long id);
	
	int delete(long id);
	
	List<Harbor> getall();
	
	Harbor getbyid(long id);
}
