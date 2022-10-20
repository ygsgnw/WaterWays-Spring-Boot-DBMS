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
				"INSERT INTO ship (ModelId,Name,Images,status,manufacturingDate) VALUES (?,?,?,?,?)",
				new Object[] { ship.getModelId() ,ship.getName(),ship.getImages(),ship.getStatus(),ship.getManufacturingDate()});
	}

	@Override
	public int update(Ship ship, long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE ship SET ModelId=?,Name=?,Images=?,status=?,manufacturingDate=? WHERE ShipId=?",
				new Object[] { ship.getModelId() ,ship.getName(),ship.getImages(),ship.getStatus(),ship.getManufacturingDate() },
				id);
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM ship WHERE ShipId=?",id);
	}

	@Override
	public List<Ship> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM ship", new BeanPropertyRowMapper<Ship>(Ship.class));
	}

	@Override
	public Ship getbyid(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM ship WHERE ShipId=?",
				new BeanPropertyRowMapper<Ship>(Ship.class), id);
	}

}
