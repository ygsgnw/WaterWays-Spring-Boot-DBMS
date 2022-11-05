package com.masters.waterways.daos;

import java.util.List;

import com.masters.waterways.models.Crew;


public interface CrewDao {

	void insert (Crew crew);
	void update (Crew crew);
	void delete (Crew crew);
	List<Crew> getAll ();
	Crew getById (int id);

	List<Crew> getAllByVoyageId (int voyageId);
}
