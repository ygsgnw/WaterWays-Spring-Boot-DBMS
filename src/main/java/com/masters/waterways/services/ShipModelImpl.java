package com.masters.waterways.services;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.ShipModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShipModelImpl implements ShipModelDao {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int insert (ShipModel ship_model) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO ShipModel (RoomCount, ModelName) VALUES (?, ?)",
				ship_model.getRoomCount(), ship_model.getModelName()
		);
	}

	@Override
	public int update (ShipModel ship_model, int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE ShipModel SET RoomCount = ?, ModelName = ? WHERE ModelId = ?",
				ship_model.getRoomCount(), ship_model.getModelName(), id
		);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"DELETE FROM ShipModel WHERE ModelId=?", id
		);
	}

	@Override
	public List<ShipModel> getAll () {
		// TODO Auto-generated method stub
		return jdbctemplate.query(
				"SELECT * FROM ShipModel",
				new BeanPropertyRowMapper<ShipModel>(ShipModel.class)
		);
	}

	@Override
	public ShipModel getById (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject(
				"SELECT * FROM ShipModel WHERE ModelId = ?",
				new BeanPropertyRowMapper<ShipModel>(ShipModel.class), id
		);
	}

}
