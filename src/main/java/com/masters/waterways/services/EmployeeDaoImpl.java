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
	public void insert (Employee employee) {
		jdbctemplate.update(
				"INSERT INTO Employee (UserId) VALUES (?, ?)",
				employee.getUserId()
		);
	}

	@Override
	public void update (Employee employee) {
		jdbctemplate.update(
				"UPDATE Employee SET UserId = ?, JoinDate = ? WHERE EmployeeId = ?",
				employee.getUserId(), employee.getJoinDate(), employee.getEmployeeId()
		);
	}

	@Override
	public void delete (Employee employee) throws RuntimeException {
		if (jdbctemplate.query(
				"SELECT EmployeeId FROM Crew, Voyage WHERE Crew.EmployeeId = ? AND Crew.VoyageId = Voyage.VoyageId AND Voyage.DepartureTime < NOW()",
				new BeanPropertyRowMapper<Integer>(Integer.class), employee.getEmployeeId()
		).isEmpty())
			jdbctemplate.update(
					"DELETE FROM Employee WHERE EmployeeId = ?",
					employee.getEmployeeId()
			);
		else
			throw new RuntimeException("Cannot dismiss crew of past voyages");
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

	@Override
	public Employee getByUserId (int id) {
		return jdbctemplate.queryForObject(
				"SELECT * FROM Employee WHERE UserId = ?",
				new BeanPropertyRowMapper<Employee>(Employee.class), id
		);
	}
}
