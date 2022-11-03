package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.Employee;

public interface EmployeeDao {
	int insert (Employee employee);
	int update (Employee employee, int id);
	int delete (int id);
	List<Employee> getAll ();
	Employee getById (int id);
	Employee getByUserId (int id);
}
