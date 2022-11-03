package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;
import org.springframework.stereotype.Repository;


public interface VoyageDao {

    int insert (Voyage voyage);
	int update (Voyage voyage,int id);
	int delete (int id);
	List<Voyage> getAll ();
	Voyage getById (int id);
	List<Voyage> getAllActive ();
	List<Voyage> getVoyagesByUserId (int userId);

}
