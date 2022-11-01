package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface roombookingdao {
	
    int save(RoomBooking roombook);
	
	int update(RoomBooking roombook,int id);
	
	int deletebytransactionid(int id);
	
	List<RoomBooking> getall();
	
	RoomBooking getbytransactionid(int id);
}
