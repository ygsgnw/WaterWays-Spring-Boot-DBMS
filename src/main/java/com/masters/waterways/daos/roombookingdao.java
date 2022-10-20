package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface roombookingdao {
	
    int save(RoomBooking roombook);
	
	int update(RoomBooking roombook,long id);
	
	int delete(long id);
	
	List<RoomBooking> getall();
	
	RoomBooking getbyid(long id);
}
