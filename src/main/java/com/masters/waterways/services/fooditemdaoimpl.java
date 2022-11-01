package com.masters.waterways.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.masters.waterways.daos.fooditemdao;
import com.masters.waterways.models.FoodItem;

@Repository
public class fooditemdaoimpl implements fooditemdao{
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int save(FoodItem fi) {
		return jdbctemplate.update(
				"INSERT INTO fooditem (VoyageId,FoodName,FoodCost,FoodDescription) VALUES (?,?,?,?)",
				new Object[] {fi.getVoyageId(),fi.getFoodName(),fi.getFoodCost(),fi.getFoodDescription()
						});
	}

	@Override
	public int update(FoodItem fi, int id) {
		return jdbctemplate.update(
				"UPDATE fooditem SET VoyageId=?,FoodName=?,FoodCost=?,FoodDescription=? WHERE FoodItemId=?",
				new Object[] { fi.getVoyageId(),fi.getFoodName(),fi.getFoodCost(),fi.getFoodDescription()},
				id);
	}

	@Override
	public int delete(int id) {

		return jdbctemplate.update("DELETE FROM fooditem WHERE FoodItemId=?",id);
	}

	@Override
	public List<FoodItem> getall() {
		return jdbctemplate.query("SELECT * FROM fooditem", new BeanPropertyRowMapper<FoodItem>( FoodItem.class));

	}

	@Override
	public FoodItem getbyid(int id) {
		return jdbctemplate.queryForObject("SELECT * FROM fooditem WHERE FoodItemId=?",
				new BeanPropertyRowMapper<>( FoodItem.class), id);
	}
}
