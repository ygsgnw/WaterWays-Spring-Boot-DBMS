package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.Employee;

public interface EmployeeDao {
	List<Employee> getAll ();
	Employee getById (int id);
	Employee getByUserId (int id);

	void makeEmployee (Employee employee);

	void setSuspended(int employeeId);
	void setActive(int employeeId);
}
