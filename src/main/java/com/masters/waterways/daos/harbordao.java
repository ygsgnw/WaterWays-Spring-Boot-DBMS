package com.masters.waterways.daos;

import java.util.List;

import com.masters.waterways.models.Harbor;

public interface harbordao {
	  int save(Harbor harbor);
		
		int update(Harbor harbor,int id);
		
		int delete(int id);
		
		List<Harbor> getall();
		
		Harbor getbyid(int id);
}
