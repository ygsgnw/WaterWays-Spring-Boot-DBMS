package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface roomtypedao {
	
    int save(RoomType roomtype);
	
	int update(RoomType roomtype,long id);
	
	int delete(long id);
	
	List<RoomType> getall();
	
	RoomType getbyid(long id);
}
