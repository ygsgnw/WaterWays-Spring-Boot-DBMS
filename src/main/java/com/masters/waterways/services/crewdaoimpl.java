package com.masters.waterways.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.masters.waterways.daos.crewdao;
import com.masters.waterways.models.Crew;
import com.masters.waterways.models.Employee;

@Repository
public class crewdaoimpl implements crewdao{
	
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int save(Crew crew) {
		return jdbctemplate.update(
				"INSERT INTO crew (UserId,ManagerId) VALUES (?,?)",
				new Object[] { employee.getUserId(), employee.getManagerId()});
	}

	@Override
	public int update(Crew crew, int id) {
		return jdbctemplate.update(
				"UPDATE crew SET UserId=?,ManagerId=? WHERE EmployeeId=?",
				new Object[] { employee.getUserId(), employee.getManagerId() },
				id);
	}

	@Override
	public int delete(int id) {
		return jdbctemplate.update("DELETE FROM   WHERE Id=?",id);
	}

	@Override
	public List<Crew> getall() {
		return jdbctemplate.query("SELECT * FROM   ", new BeanPropertyRowMapper<>( .class));

	}

	@Override
	public Crew getbyid(int id) {
		return jdbctemplate.queryForObject("SELECT * FROM    WHERE Id=?",
				new BeanPropertyRowMapper<>( .class), id);
	}
	
}
