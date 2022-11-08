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
	public void insert (Crew crew) {
		jdbctemplate.update(
				"INSERT INTO crew (EmployeeId, VoyageId, CrewRole) VALUES (?, ?, ?)",
				crew.getEmployeeId(), crew.getVoyageId(), crew.getCrewRole()
		);
	}

	@Override
	public void updateRole (Crew crew) {
		jdbctemplate.update(
				"UPDATE crew SET CrewRole = ? WHERE EmployeeId = ? AND VoyageId = ?",
				crew.getCrewRole(), crew.getEmployeeId(), crew.getVoyageId()
		);
	}

	@Override
	public void delete (Crew crew) {
		jdbctemplate.update(
				"DELETE FROM crew WHERE EmployeeId = ? AND VoyageId = ?",
				crew.getEmployeeId(), crew.getVoyageId()
		);
	}

	@Override
	public List<Crew> getAll () {
		return jdbctemplate.query(
				"SELECT * FROM crew",
				new BeanPropertyRowMapper<>(Crew.class)
		);
	}


    @Override
    public List<Crew> getAllByVoyageId(int voyageId) {
		return jdbctemplate.query(
				"SELECT * FROM Crew WHERE VoyageId = ?",
				new BeanPropertyRowMapper<>(Crew.class), voyageId
		);
    }

	@Override
	public List<Integer> getAllNotCrewEmployee(int voyageId) {
		return null;
	}

}
