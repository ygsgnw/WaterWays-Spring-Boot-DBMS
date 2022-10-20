package com.masters.waterways.services;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.FoodBooking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class foodbookingdaoimpl implements foodbookingdao {

	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int save(FoodBooking fb) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO Employee (TypeId,VoyageId,TransactionId,PassengerId,Status) VALUES (?,?,?,?,?)",
				new Object[] { fb.getTypeId(),fb.getVoyageId() ,fb.getTransactionId(),fb.getPassengerId(),fb.getStatus()});
	}

	@Override
	public int update(FoodBooking fb, long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE foodbooking SET TypeId=?,Voyage=?,TransactionId=?,PassengerId=?,Status=? WHERE FoodBookingId=?",
				new Object[] {fb.getTypeId(),fb.getVoyageId(),fb.getTransactionId(),fb.getPassengerId(),fb.getStatus() },
				id);
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM foodbooking WHERE FoodBookingId=?", id);
	}

	@Override
	public List<FoodBooking> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM foodbooking",
				new BeanPropertyRowMapper<FoodBooking>(FoodBooking.class));
	}

	@Override
	public FoodBooking getbyid(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM foodbooking WHERE FoodBookingId=?",
				new BeanPropertyRowMapper<FoodBooking>(FoodBooking.class), id);
	}

}
