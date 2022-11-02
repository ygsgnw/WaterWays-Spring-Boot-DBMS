package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;
import org.springframework.stereotype.Repository;


public interface VoyageDao {
	
    int save(Voyage voyage);
	
	int update(Voyage voyage,int id);
	
	int delete(int id);
	
	List<Voyage> getall();
	
	Voyage getbyid(int id);

    List<Voyage> getVoyagesByUserId(int userId);
}
