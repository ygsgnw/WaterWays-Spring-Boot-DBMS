package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.Employee;

public interface employeedao{
  
	int save(Employee employee);
	
	int update(Employee employee,long id);
	
	int delete(long id);
	
	List<Employee> getall();
	
	Employee getbyid(long id);
	
}
