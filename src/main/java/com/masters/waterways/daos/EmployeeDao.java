package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.Employee;

public interface EmployeeDao {
	void insert (Employee employee);
	void delete (Employee employee);
	List<Employee> getAll ();
	Employee getById (int id);
	Employee getByUserId (int id);

	void makeEmployee (Employee employee);

	void setSuspended(int employeeId);
	void setActive(int employeeId);

	Boolean isEmployee(int id);

}
