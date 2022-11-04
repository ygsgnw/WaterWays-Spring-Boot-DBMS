package com.masters.waterways.services;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.FoodBooking;
import java.util.List;
import com.masters.waterways.models.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FoodBookingDaoImpl implements FoodBookingDao {

	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public void insert (FoodBooking foodBooking) {
		// TODO Auto-generated method stub
		jdbctemplate.update(
				"INSERT INTO FoodBooking (FoodItemId, VoyageId, FoodItemCount, TransactionId) VALUES (?, ?, ?, ?)",
				foodBooking.getFoodItemId(), foodBooking.getVoyageId(), foodBooking.getFoodItemCount(), foodBooking.getTransactionId()
		);
	}


	@Override
	public List<FoodBooking> getAll () {
		// TODO Auto-generated method stub
		return jdbctemplate.query(
				"SELECT * FROM FoodBooking",
				new BeanPropertyRowMapper<FoodBooking>(FoodBooking.class)
		);
	}

	@Override
	public FoodBooking getById (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject(
				"SELECT * FROM FoodBooking WHERE TransactionId = ?",
				new BeanPropertyRowMapper<FoodBooking>(FoodBooking.class), id
		);
	}

	@Override
	public void bookFood(int userId, int roomId, int foodCount, FoodItem foodItem) {

	}


}
