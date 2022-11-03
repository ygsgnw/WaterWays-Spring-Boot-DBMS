package com.masters.waterways.daos;

import java.util.List;

import com.masters.waterways.models.Crew;


public interface CrewDao {
	int insert (Crew crew);
	int update (Crew crew, int id);
	int delete (int id);
	List<Crew> getAll ();
	Crew getById (int id);
}
