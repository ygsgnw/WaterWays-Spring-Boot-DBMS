package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;
import org.springframework.stereotype.Repository;


public interface VoyageDao {

    void insert (Voyage voyage);
	int delete (int id);
	List<Voyage> getAll ();
	Voyage getById (int id);

	List<Voyage> getAllActive ();
	List<Voyage> getAllActiveByUserId (int userId);
	List<Voyage> getAllCompletedByUserId (int userId);

	void setSuspended (int voyageId);

	void updateVoyageByFare(int voyageId, int fare);

	boolean isVoyageCompletedByVoyageId(int voyageId);
}
