package com.masters.waterways.daos;

import java.util.List;

import com.masters.waterways.models.Crew;
import com.masters.waterways.models.Employee;


public interface CrewDao {

	void insert (Crew crew);
	void updateRole (Crew crew);
	void delete (Crew crew);
	List<Crew> getAll ();

	List<Crew> getAllByVoyageId (int voyageId);

	List<Employee> getAllAvailableEmployees(int voyageId);
}
