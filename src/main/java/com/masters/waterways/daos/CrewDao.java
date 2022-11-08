package com.masters.waterways.daos;

import java.util.List;

import com.masters.waterways.models.Crew;


public interface CrewDao {

	void insert (Crew crew);
	void updateRole (Crew crew);
	void delete (Crew crew);
	List<Crew> getAll ();

	List<Crew> getAllByVoyageId (int voyageId);

	List<Integer> getAllNotCrewEmployee(int voyageId);
}
