package com.masters.waterways.services;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.Voyage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VoyageDaoImpl implements VoyageDao {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int insert (Voyage voyage) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO Voyage (ShipSerialId, Fare, ArrivalHarborId, ArrivalTime, DepartureHarborId, DepartureTime, VoyageStatusCode) VALUES (?, ?, ?, ?, ?, ?, ?)",
				voyage.getShipSerialId(), voyage.getFare(), voyage.getArrivalHarborId(), voyage.getArrivalTime(), voyage.getDepartureHarborId(), voyage.getDepartureTime(), voyage.getVoyageStatusCode()
		);
	}

	@Override
	public int update (Voyage voyage, int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE Voyage SET ShipSerialId = ?, Fare = ?, ArrivalHarborId = ?, ArrivalTime = ?, DepartureHarborId = ?, DepartureTime = ? WHERE VoyageId = ?",
				voyage.getShipSerialId(), voyage.getFare(), voyage.getArrivalHarborId(), voyage.getArrivalTime(), voyage.getDepartureHarborId(), voyage.getDepartureTime(), id
		);
	}

	@Override
	public int delete (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"DELETE FROM Voyage WHERE VoyageId = ?",
				id
		);
	}

	@Override
	public List<Voyage> getAll () {
		// TODO Auto-generated method stub
		return jdbctemplate.query(
				"SELECT * FROM Voyage",
				new BeanPropertyRowMapper<Voyage>(Voyage.class)
		);
	}

	@Override
	public Voyage getById (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject(
				"SELECT * FROM Voyage WHERE VoyageId = ?",
				new BeanPropertyRowMapper<Voyage>(Voyage.class), id
		);
	}

	@Override
	public List<Voyage> getAllActive () {
		return jdbctemplate.query(
				"SELECT * FROM Voyage WHERE DepartureTime > NOW()",
				new BeanPropertyRowMapper<Voyage>(Voyage.class)
		);
	}

	@Override
	public List<Voyage> getVoyagesByUserId(int userId) {

	}

}
