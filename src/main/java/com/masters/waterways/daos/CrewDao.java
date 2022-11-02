package com.masters.waterways.daos;

import java.util.List;

import com.masters.waterways.models.Crew;


public interface CrewDao {

	int save(Crew crew);
	
	int update(Crew crew,int id);
	
	int delete(int id);
	
	List<Crew> getall();
	
	Crew getbyid(int id);
}
