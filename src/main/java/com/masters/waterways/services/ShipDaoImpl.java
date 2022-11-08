package com.masters.waterways.services;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.Ship;

import java.util.List;

import com.masters.waterways.models.ShipStatusProvider;
import com.masters.waterways.models.VoyageStatusProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShipDaoImpl implements ShipDao {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int insert (Ship ship) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO Ship (ModelId, ShipStatusCode, MfDate) VALUES (?, ?, ?)",
				ship.getModelId(), ship.getShipStatusCode(), ship.getMfDate());
	}

	@Override
	public List<Ship> getAll () {
		// TODO Auto-generated method stub
		return jdbctemplate.query(
				"SELECT * FROM Ship",
				new BeanPropertyRowMapper<>(Ship.class)
		);
	}

	@Override
	public List<Ship> getAllOperational () {
		return jdbctemplate.query(
				"SELECT * FROM Ship WHERE ShipStatusCode = ?",
				new BeanPropertyRowMapper<>(Ship.class), ShipStatusProvider.getShipStatusCode.get("OPERATIONAL")
		);
	}

	@Override
	public Ship getById (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM Ship WHERE ShipSerialId=?",
				new BeanPropertyRowMapper<>(Ship.class), id);
	}

	@Override
	public int setOperational(int id) {
		return jdbctemplate.update(
				"UPDATE Ship SET ShipStatusCode = ? WHERE ShipSerialId = ?",
				ShipStatusProvider.getShipStatusCode.get("OPERATIONAL"), id
		);
	}

	@Override
	public int setSuspended(int id) {

		jdbctemplate.update(
				"UPDATE Voyage SET VoyageStatusCode = ? WHERE ShipSerialId = ? AND DepartureTime > NOW()"
		);

		return jdbctemplate.update(
				"UPDATE Ship SET ShipStatusCode = ? WHERE ShipSerialId = ?",
				ShipStatusProvider.getShipStatusCode.get("SUSPENDED"), id
		);
	}
}
