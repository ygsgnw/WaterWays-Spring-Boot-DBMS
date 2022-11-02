package com.masters.waterways.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.masters.waterways.daos.CrewDao;
import com.masters.waterways.models.Crew;

@Repository
public class CrewDaoImpl implements CrewDao {
	
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int insert (Crew crew) {
		return jdbctemplate.update(
				"INSERT INTO crew (CrewId, EmployeeId, VoyageId, Role) VALUES (?, ?, ?, ?)",
				crew.getCrewId(), crew.getEmployeeId(), crew.getVoyageId(), crew.getRole()
		);
	}

	@Override
	public int update (Crew crew, int id) {
		return jdbctemplate.update(
				"UPDATE crew SET EmployeeId = ?, VoyageId = ?, Role = ? WHERE CrewId = ?",
				crew.getEmployeeId(), crew.getVoyageId(), crew.getRole(), id
		);
	}

	@Override
	public int delete (int id) {
		return jdbctemplate.update(
				"DELETE FROM crew WHERE CrewId=?",
				id
		);
	}

	@Override
	public List<Crew> getAll () {
		return jdbctemplate.query(
				"SELECT * FROM crew",
				new BeanPropertyRowMapper<Crew>(Crew.class)
		);
	}

	@Override
	public Crew getById (int id) {
		return jdbctemplate.queryForObject(
				"SELECT * FROM crew WHERE CrewId=?",
				new BeanPropertyRowMapper<Crew>( Crew.class), id
		);
	}
	
}
