package com.masters.waterways.services;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.Ship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class shipdaoimpl implements shipdao{

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int save(Ship ship) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO ship (ModelId,ShipStatus,MfDate) VALUES (?,?,?)",
				new Object[] { ship.getModelId() ,ship.getShipStatus(),ship.getMfDate()});
	}

	@Override
	public int update(Ship ship, int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE ship SET ModelId=?,ShipStatus=?,MfDate=? WHERE ShipSerialId=?",
				new Object[] { ship.getModelId() ,ship.getShipStatus(),ship.getMfDate(),id });
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM ship WHERE ShipSerialId=?",id);
	}

	@Override
	public List<Ship> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM ship", new BeanPropertyRowMapper<Ship>(Ship.class));
	}

	@Override
	public Ship getbyid(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM ship WHERE ShipSerialId=?",
				new BeanPropertyRowMapper<Ship>(Ship.class), id);
	}

}
