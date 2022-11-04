package com.masters.waterways.services;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.RoomBooking;

import java.util.List;

import com.masters.waterways.models.RoomStatusProvider;
import com.masters.waterways.models.Voyage;
import com.masters.waterways.models.VoyageStatusProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoomBookingDaoImpl implements RoomBookingDao {

	@Autowired
	JdbcTemplate jdbctemplate;

	@Autowired
	TransactionDao transactionDao;

	VoyageDao voyageDao;
	
	@Override
	public int insert (RoomBooking room_booking) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO RoomBooking (RoomId, VoyageId, TransactionId, RoomStatusCode) VALUES (?, ?, ?, ?)",
				room_booking.getRoomId(), room_booking.getVoyageId(), room_booking.getTransactionId(), room_booking.getRoomStatusCode()
		);
	}

	@Override
	public int update(RoomBooking room_booking) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE RoomBooking SET RoomStatusCode = ? WHERE VoyageId = ? AND RoomId = ?",
				room_booking.getRoomStatusCode(), room_booking.getVoyageId(), room_booking.getRoomId()
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

    @Override
	public List<RoomBooking> getRoomsByUserIdAndVoyageId (int userId, int voyageId) {
		return jdbctemplate.query(
				"SELECT RoomBooking.TransactionId, RoomBooking.RoomId, RoomBooking.VoyageId, RoomBooking.RoomStatusCode " +
						"FROM Transaction, RoomBooking " +
						"WHERE UserId = ? " +
						"AND RoomBooking.TransactionId = Transaction.TransactionId " +
						"AND VoyageId = ?",
				new BeanPropertyRowMapper<RoomBooking>(RoomBooking.class), userId, voyageId
		);
	}


	@Override
	public void roomBookingByVoyageIdAndUserId(int voyageId, int userId) throws RuntimeException {
		Voyage voyage = voyageDao.getById(voyageId);
		if (voyage.getVoyageStatusCode() != VoyageStatusProvider.getVoyageStatusCode.get("OPERATIONAL"))
			throw new RuntimeException("Voyage is not operational");
		try {
			RoomBooking reservedRoom = reserveRoomByVoyageId(voyageId);
			roomBookingByReservedRoomIdAndUserId(reservedRoom, userId, voyage.getFare());
		} catch (Exception exception) {
			System.out.println("Room booking failed");
		}

	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public RoomBooking reserveRoomByVoyageId (int voyageId) {
		RoomBooking room = jdbctemplate.queryForObject(
				"SELECT * FROM RoomBooking WHERE VoyageId = ? AND RoomStatusCode = ?",
				new BeanPropertyRowMapper<RoomBooking>(RoomBooking.class), voyageId, RoomStatusProvider.getRoomStatusCode.get("AVAILABLE")
		);

		if (room != null) {
			room.setRoomStatusCode(RoomStatusProvider.getRoomStatusCode.get("RESERVED"));
			update(room);
			return room;
		} else
			throw new RuntimeException("No available rooms");
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void roomBookingByReservedRoomIdAndUserId(RoomBooking room, int userId, int fare) {
		assert(room != null);

		jdbctemplate.update(
				"INSERT INTO Transaction (TransactionDate, Amount, UserId) VALUE (NOW(), ?, ?)",
				fare, userId
		);

		Integer transactionId = jdbctemplate.queryForObject(
				"SELECT LAST_INSERT_ID() FROM Transaction",
				new BeanPropertyRowMapper<Integer>(Integer.class)
		);

		if (transactionId != null) {
			room.setTransactionId(transactionId);
			room.setRoomStatusCode(RoomStatusProvider.getRoomStatusCode.get("BOOKED"));
			update(room);
		} else
			throw new RuntimeException("Transaction failed");
	}


}
