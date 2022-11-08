package com.masters.waterways.services;

import java.util.List;

import com.masters.waterways.models.Employee;
import com.masters.waterways.models.Voyage;
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
				"INSERT INTO Crew (EmployeeId, VoyageId, CrewRole) VALUES (?, ?, ?)",
				crew.getEmployeeId(), crew.getVoyageId(), crew.getCrewRole()
		);
	}

	@Override
	public void updateRole (Crew crew) {
		jdbctemplate.update(
				"UPDATE Crew SET CrewRole = ? WHERE EmployeeId = ? AND VoyageId = ?",
				crew.getCrewRole(), crew.getEmployeeId(), crew.getVoyageId()
		);
	}

	@Override
	public void delete (Crew crew) {
		jdbctemplate.update(
				"DELETE FROM Crew WHERE EmployeeId = ? AND VoyageId = ?",
				crew.getEmployeeId(), crew.getVoyageId()
		);
	}

	@Override
	public List<Crew> getAll () {
		return jdbctemplate.query(
				"SELECT * FROM Crew",
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
	public List<Employee> getAllAvailableEmployees(int voyageId) throws RuntimeException {

		Voyage v = jdbctemplate.queryForObject(
				"SELECT * FROM Voyage WHERE VoyageId = ?",
				new BeanPropertyRowMapper<>(Voyage.class), voyageId
		);

		if (v == null)
			throw new RuntimeException("Voyage doesn't exist");

		return jdbctemplate.query(
				"SELECT * FROM Employee WHERE EmployeeStatusCode = 1 AND NOT EXISTS (SELECT * FROM Crew INNER JOIN Voyage ON Crew.VoyageId = Voyage.VoyageId AND Crew.EmployeeId = Employee.EmployeeId WHERE Employee.EmployeeId = Crew.EmployeeId AND Voyage.DepartureTime < ? AND Voyage.ArrivalTime > ?)",
				new BeanPropertyRowMapper<>(Employee.class), v.getArrivalTime(), v.getDepartureTime()
		);
	}

}
