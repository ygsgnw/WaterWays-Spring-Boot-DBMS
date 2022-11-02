package com.masters.waterways.services;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.FoodBooking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FoodBookingDaoImpl implements FoodBookingDao {

	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int insert (FoodBooking fb) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO FoodBooking (FoodItemId, VoyageId, FoodItemCount, TransactionId) VALUES (?, ?, ?, ?)",
				fb.getFoodItemId(), fb.getVoyageId(), fb.getFoodItemId(), fb.getTransactionId()
		);
	}

	@Override
	public int update (FoodBooking fb, int id) {
		// TODO Auto-generated method stu
		return jdbctemplate.update(
				"UPDATE FoodBooking SET FoodItemId = ?, VoyageId = ?, FoodItemCount = ? WHERE TransactionId = ?",
				fb.getFoodItemId(), fb.getVoyageId(), fb.getFoodItemCount(), id
		);
	}

	@Override
	public int delete (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"DELETE FROM FoodBooking WHERE TransactionId = ?",
				id
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
}
