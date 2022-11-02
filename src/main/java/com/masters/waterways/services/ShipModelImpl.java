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
	public int save(ShipModel shipmodel) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO shipmodel (RoomCount,ModelName) VALUES (?,?)",
				new Object[] { shipmodel.getRoomCount(),shipmodel.getModelName() });
	}

	@Override
	public int update(ShipModel shipmodel, int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE shipmodel SET RoomCount=?,ModelName=? WHERE ModelId=?",
				new Object[] { shipmodel.getRoomCount(),shipmodel.getModelName() ,id});
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM shipmodel WHERE ModelId=?",id);
	}

	@Override
	public List<ShipModel> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM shipmodel", new BeanPropertyRowMapper<ShipModel>(ShipModel.class));
	}

	@Override
	public ShipModel getbyid(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM shipmodel WHERE ModelId=?",
				new BeanPropertyRowMapper<ShipModel>(ShipModel.class), id);
	}

}
