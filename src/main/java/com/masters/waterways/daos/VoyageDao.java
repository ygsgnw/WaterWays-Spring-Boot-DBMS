package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;
import org.springframework.stereotype.Repository;


public interface VoyageDao {

    int insert (Voyage voyage);
	List<Voyage> getAll ();
	Voyage getById (int id);

	List<Voyage> getAllActive ();
	List<Voyage> getAllActiveByUserId (int userId);
	List<Voyage> getAllCompletedByUserId (int userId);

	int setSuspended (int voyageId);

	int setOperational(int voyageId);

	int updateVoyageByFare(int voyageId, int fare);

	boolean isVoyageCompletedByVoyageId(int voyageId);
}
