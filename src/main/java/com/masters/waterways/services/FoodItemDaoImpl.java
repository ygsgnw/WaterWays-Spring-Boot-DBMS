package com.masters.waterways.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.masters.waterways.daos.FoodItemDao;
import com.masters.waterways.models.FoodItem;

@Repository
public class FoodItemDaoImpl implements FoodItemDao {
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int insert (FoodItem fi) {
		return jdbctemplate.update(
				"INSERT INTO FoodItem (VoyageId, FoodName, FoodCost, FoodDescription) VALUES (?, ?, ?, ?)",
				fi.getVoyageId(), fi.getFoodName(), fi.getFoodCost(), fi.getFoodDescription()
		);
	}

	@Override
	public int update (FoodItem fi, int id) {
		return jdbctemplate.update(
				"UPDATE FoodItem SET VoyageId = ?, FoodName = ?, FoodCost = ?, FoodDescription = ? WHERE FoodItemId = ?",
				fi.getVoyageId(), fi.getFoodName(), fi.getFoodCost(), fi.getFoodDescription(), id
		);
	}

	@Override
	public int delete (int id) {
		return jdbctemplate.update(
				"DELETE FROM FoodItem WHERE FoodItemId = ?",id
		);
	}

	@Override
	public List<FoodItem> getAll () {
		return jdbctemplate.query(
				"SELECT * FROM FoodItem",
				new BeanPropertyRowMapper<FoodItem>(FoodItem.class)
		);

	}

	@Override
	public FoodItem getById (int id) {
		return jdbctemplate.queryForObject(
				"SELECT * FROM FoodItem WHERE FoodItemId = ?",
				new BeanPropertyRowMapper<>( FoodItem.class), id
		);
	}
}
