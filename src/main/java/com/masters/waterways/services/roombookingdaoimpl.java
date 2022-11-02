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
				"INSERT INTO roombooking (RoomId,VoyageId,Fare,TransactionId,RoomStatus) VALUES (?,?,?,?,?)",
				new Object[] {roombook.getRoomId(), roombook.getVoyageId(),roombook.getFare(),roombook.getTransactionId(),roombook.getRoomStatus() });
	}

	@Override
	public int update(RoomBooking roombook, int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE roombooking SET RoomId=?,VoyageId=?,Fare=?,RoomStatus=? WHERE TransactionId=?",
				new Object[] { roombook.getRoomId(), roombook.getVoyageId(),roombook.getFare(),roombook.getRoomStatus() ,id});
	}

	@Override
	public int deletebytransactionid(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM roombooking WHERE TransactionId=?",id);
	}

	@Override
	public List<RoomBooking> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM roombooking", new BeanPropertyRowMapper<RoomBooking>(RoomBooking.class));
	}

	@Override
	public RoomBooking getbytransactionid(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM roombooking WHERE TransactionId=?",
				new BeanPropertyRowMapper<RoomBooking>(RoomBooking.class), id);
	}

}
