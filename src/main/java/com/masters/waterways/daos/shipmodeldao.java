package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface shipmodeldao {
	
    int save(ShipModel shipmodel);
	
	int update(ShipModel shipmodel,long id);
	
	int delete(long id);
	
	List<ShipModel> getall();
	
	ShipModel getbyid(long id);
}
