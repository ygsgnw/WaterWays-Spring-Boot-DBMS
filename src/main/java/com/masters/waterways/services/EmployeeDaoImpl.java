package com.masters.waterways.services;

import java.util.List;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int insert (Employee employee) {
		return jdbctemplate.update(
				"INSERT INTO Employee (UserId, ManagerId) VALUES (?, ?)",
				employee.getUserId(), employee.getManagerId()
		);
	}

	@Override
	public int update (Employee employee, int id) {
		return jdbctemplate.update(
				"UPDATE Employee SET UserId=?, ManagerId=? WHERE EmployeeId=?",
				employee.getUserId(), employee.getManagerId(), id
		);
	}

	@Override
	public int delete (int id) {
		return jdbctemplate.update(
				"DELETE FROM Employee WHERE EmployeeId = ?",
				id
		);
	}

	@Override
	public List<Employee> getAll () {
		return jdbctemplate.query(
				"SELECT * FROM Employee",
				new BeanPropertyRowMapper<Employee>(Employee.class)
		);
	}

	@Override
	public Employee getById (int id) {
		return jdbctemplate.queryForObject(
				"SELECT * FROM Employee WHERE EmployeeId = ?",
				new BeanPropertyRowMapper<Employee>(Employee.class), id
		);
	}

}
