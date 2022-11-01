package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface shipmodeldao {
	
    int save(ShipModel shipmodel);
	
	int update(ShipModel shipmodel,int id);
	
	int delete(int id);
	
	List<ShipModel> getall();
	
	ShipModel getbyid(int id);
}
