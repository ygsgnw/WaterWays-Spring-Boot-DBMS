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
	public int save(FoodBooking fb) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO foodbooking (FoodItemId,VoyageId,FoodItemCount,TransactionId) VALUES (?,?,?,?)",
				new Object[] { fb.getFoodItemId(),fb.getVoyageId(),fb.getFoodItemId(),fb.getTransactionId()});
	}

	@Override
	public int update(FoodBooking fb, int id) {
		// TODO Auto-generated method stu
		return jdbctemplate.update(
				"UPDATE foodbooking SET FoodItemId=?,VoyageId=?,FoodItemCount=? WHERE TransactionId=?",
				new Object[] {fb.getFoodItemId(),fb.getVoyageId(),fb.getFoodItemCount(),id });
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM foodbooking WHERE TransactionId=?", id);
	}

	@Override
	public List<FoodBooking> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM foodbooking",
				new BeanPropertyRowMapper<FoodBooking>(FoodBooking.class));
	}

	@Override
	public FoodBooking getbyid(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM foodbooking WHERE TransactionId=?",
				new BeanPropertyRowMapper<FoodBooking>(FoodBooking.class), id);
	}

	@Override
	public void bookFood(int userId, int voyageId, int roomId, int foodItemId, int foodCount) {

	}

}
