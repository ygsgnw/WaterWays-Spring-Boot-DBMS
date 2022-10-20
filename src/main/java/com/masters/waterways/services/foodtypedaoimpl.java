package com.masters.waterways.services;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.FoodType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class foodtypedaoimpl implements foodtypedao{
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int save(FoodType foodtype) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO Employee (Name,Cost,Image,Description) VALUES (?,?,?,?)",
				new Object[] { foodtype.getName(),foodtype.getCost(),foodtype.getImage(),foodtype.getDescription()});
	}

	@Override
	public int update(FoodType foodtype, long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE employee SET Name=?,Cost=?,Image=?,Description=? WHERE FoodTypeId=?",
				new Object[] { foodtype.getName(),foodtype.getCost(),foodtype.getImage(),foodtype.getDescription() },
				id);
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM foodtype WHERE FoodTypeId=?",id);
	}

	@Override
	public List<FoodType> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM foodtype", new BeanPropertyRowMapper<FoodType>(FoodType.class));
	}

	@Override
	public FoodType getbyid(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM foodtype WHERE FoodTypeId=?",
				new BeanPropertyRowMapper<FoodType>(FoodType.class), id);
	}

}
