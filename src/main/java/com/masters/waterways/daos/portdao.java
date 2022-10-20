package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface portdao {
	
    int save(Port port);
	
	int update(Port port,long id);
	
	int delete(long id);
	
	List<Port> getall();
	
	Port getbyid(long id);
}
