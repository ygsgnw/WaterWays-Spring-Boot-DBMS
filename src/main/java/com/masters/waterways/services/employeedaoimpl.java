package com.masters.waterways.services;

import java.util.List;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class employeedaoimpl implements employeedao {

	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int save(Employee employee) {
		return jdbctemplate.update(
				"INSERT INTO employee (UserId,ManagerId) VALUES (?,?)",
				new Object[] { employee.getUserId(), employee.getManagerId()});
	}

	@Override
	public int update(Employee employee, long id) {
		return jdbctemplate.update(
				"UPDATE employee SET UserId=?,ManagerId=? WHERE EmployeeId=?",
				new Object[] { employee.getUserId(), employee.getManagerId() },
				id);
	}

	@Override
	public int delete(long id) {
		return jdbctemplate.update("DELETE FROM employee WHERE EmployeeId=?",id);
	}

	@Override
	public List<Employee> getall() {
		return jdbctemplate.query("SELECT * FROM employee", new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Override
	public Employee getbyid(long id) {
		return jdbctemplate.queryForObject("SELECT * FROM employee WHERE EmployeeId=?",
				new BeanPropertyRowMapper<Employee>(Employee.class), id);
	}

}
