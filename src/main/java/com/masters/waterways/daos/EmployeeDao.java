package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.Employee;

public interface EmployeeDao {
	void insert (Employee employee);
	void update (Employee employee);
	void delete (Employee employee);
	List<Employee> getAll ();
	Employee getById (int id);
	Employee getByUserId (int id);
	void makeEmployeeByUserId (int id);

	void setSuspended(int employeeId);
}
