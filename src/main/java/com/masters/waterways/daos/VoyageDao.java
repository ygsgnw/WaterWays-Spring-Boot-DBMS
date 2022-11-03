package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;
import org.springframework.stereotype.Repository;


public interface VoyageDao {
<<<<<<< HEAD
	
    int save(Voyage voyage);
	
	int update(Voyage voyage,int id);
	
	int delete(int id);
	
	List<Voyage> getall();
	
	Voyage getbyid(int id);

    List<Voyage> getVoyagesByUserId(int userId);
=======
    int insert (Voyage voyage);
	int update (Voyage voyage,int id);
	int delete (int id);
	List<Voyage> getAll ();
	Voyage getById (int id);
	List<Voyage> getAllActive ();
	List<Voyage> getVoyagesByUserId (int userId);
>>>>>>> 2f755b16371c6dbfff0525b7e27e6a15f162674d
}
