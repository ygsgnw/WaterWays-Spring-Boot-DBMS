package com.masters.waterways.services;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.Ship;

import java.util.List;

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
				"INSERT INTO Ship (ModelId,ShipStatusCode,MfDate) VALUES (?,?,?)",
				ship.getModelId(),ship.getShipStatusCode(),ship.getMfDate());
	}

	@Override
	public int update (Ship ship, int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE Ship SET ModelId=?,ShipStatusCode=?,MfDate=? WHERE ShipSerialId=?",
				ship.getModelId(),ship.getShipStatusCode(),ship.getMfDate(),id);
	}

	@Override
	public int delete (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM Ship WHERE ShipSerialId=?",id);
	}

	@Override
	public List<Ship> getAll () {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM Ship", new BeanPropertyRowMapper<Ship>(Ship.class));
	}

	@Override
	public Ship getById (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM Ship WHERE ShipSerialId=?",
				new BeanPropertyRowMapper<Ship>(Ship.class), id);
	}

    @Override
    public List<ShipStatus> getAllShipStatuses() {
		return jdbctemplate.query(
				"SELECT * FROM SHIP_STATUS",
				new BeanPropertyRowMapper<ShipStatus>(ShipStatus.class)
		);
    }

}
