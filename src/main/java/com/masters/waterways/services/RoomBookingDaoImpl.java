package com.masters.waterways.services;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.RoomBooking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoomBookingDaoImpl implements RoomBookingDao {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int insert (RoomBooking room_booking) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO RoomBooking (RoomId, VoyageId, TransactionId, RoomStatus) VALUES (?, ?, ?, ?)",
				room_booking.getRoomId(), room_booking.getVoyageId(), room_booking.getTransactionId(), room_booking.getRoomStatus()
		);
	}

	@Override
	public int update (RoomBooking room_booking, int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE RoomBooking SET RoomId = ?, VoyageId = ?, RoomStatus = ? WHERE TransactionId = ?",
				room_booking.getRoomId(), room_booking.getVoyageId(), room_booking.getRoomStatus(), id
		);
	}

	@Override
	public int delete (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM RoomBooking WHERE TransactionId = ?", id
		);
	}

	@Override
	public List<RoomBooking> getAll() {
		// TODO Auto-generated method stub
		return jdbctemplate.query(
				"SELECT * FROM RoomBooking",
				new BeanPropertyRowMapper<RoomBooking>(RoomBooking.class)
		);
	}

	@Override
	public RoomBooking getById (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject(
				"SELECT * FROM RoomBooking WHERE TransactionId = ?",
				new BeanPropertyRowMapper<RoomBooking>(RoomBooking.class), id
		);
	}

}
