package com.masters.waterways.services;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.RoomBooking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class roombookingdaoimpl implements roombookingdao{

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int save(RoomBooking roombook) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO roombooking (VoyageId,TransactionId,PassengerId,Status,RoomId) VALUES (?,?,?,?,?)",
				new Object[] { roombook.getVoyageId(),roombook.getTransactionId(),roombook.getPassengerId(),roombook.getStatus(),roombook.getRoomId() });
	}

	@Override
	public int update(RoomBooking roombook, long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE roombooking SET VoyageId=?,TransactionId=?,PassengerId=?,Status=?,RoomId=? WHERE RoomBookingId=?",
				new Object[] { roombook.getVoyageId(),roombook.getTransactionId(),roombook.getPassengerId(),roombook.getStatus(),roombook.getRoomId() },
				id);
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM roombooking WHERE RoomBookingId=?",id);
	}

	@Override
	public List<RoomBooking> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM roombooking", new BeanPropertyRowMapper<RoomBooking>(RoomBooking.class));
	}

	@Override
	public RoomBooking getbyid(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM roombooking WHERE RoomBookingId=?",
				new BeanPropertyRowMapper<RoomBooking>(RoomBooking.class), id);
	}

}
