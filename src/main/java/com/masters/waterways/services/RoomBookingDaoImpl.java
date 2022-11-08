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
	
//	@Override
//	public int insert(RoomBooking room_booking) {
//		// TODO Auto-generated method stub
//		return jdbctemplate.update(
//				"INSERT INTO RoomBooking (RoomId, VoyageId, TransactionId, RoomStatusCode) VALUES (?, ?, ?, ?)",
//				room_booking.getRoomId(), room_booking.getVoyageId(), room_booking.getTransactionId(), room_booking.getRoomStatusCode()
//		);
//	}

//	@Override
//	public int update(RoomBooking room_booking) {
//		// TODO Auto-generated method stub
//		return jdbctemplate.update(
//				"UPDATE RoomBooking SET RoomStatusCode = ? WHERE VoyageId = ? AND RoomId = ?",
//				room_booking.getRoomStatusCode(), room_booking.getVoyageId(), room_booking.getRoomId()
//		);
//	}

//	@Override
//	public int delete(int id) {
//		// TODO Auto-generated method stub
//		return jdbctemplate.update("DELETE FROM RoomBooking WHERE TransactionId = ?", id
//		);
//	}

//	@Override
//	public List<RoomBooking> getAll() {
//		// TODO Auto-generated method stub
//		return jdbctemplate.query(
//				"SELECT * FROM RoomBooking",
//				new BeanPropertyRowMapper<>(RoomBooking.class)
//		);
//	}

	@Override
	public RoomBooking getByTransactionId(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject(
				"SELECT * FROM RoomBooking WHERE TransactionId = ?",
				new BeanPropertyRowMapper<>(RoomBooking.class), id
		);
	}

    @Override
    public List<RoomBooking> getAllByVoyageId(int voyageId) {
		return jdbctemplate.query(
				"SELECT * FROM RoomBooking WHERE VoyageId = ?",
				new BeanPropertyRowMapper<>(RoomBooking.class), voyageId
		);
    }

    @Override
	public List<RoomBooking> getAllByUserIdAndVoyageId(int userId, int voyageId) {
		return jdbctemplate.query(
				"SELECT RoomBooking.TransactionId, RoomBooking.RoomId, RoomBooking.VoyageId, RoomBooking.RoomStatusCode " +
						"FROM Transaction, RoomBooking " +
						"WHERE UserId = ? " +
						"AND RoomBooking.TransactionId = Transaction.TransactionId " +
						"AND VoyageId = ?",
				new BeanPropertyRowMapper<>(RoomBooking.class), userId, voyageId
		);
	}


	@Override
	public void bookRoomByVoyageIdAndUserId(int voyageId, int userId) throws RuntimeException {
		Voyage voyage = voyageDao.getById(voyageId);
		if (voyage.getVoyageStatusCode() != VoyageStatusProvider.getVoyageStatusCode.get("OPERATIONAL"))
			throw new RuntimeException("Voyage is not operational");
		try {
			RoomBooking reservedRoom = reserveRoomByVoyageId(voyageId);
			bookReservedRoomByUserId(reservedRoom, userId, voyage.getFare());
		} catch (Exception exception) {
			System.out.println("Room booking failed");
		}

	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public RoomBooking reserveRoomByVoyageId(int voyageId) {
		RoomBooking room = jdbctemplate.queryForObject(
				"SELECT * FROM RoomBooking WHERE VoyageId = ? AND RoomStatusCode = ?",
				new BeanPropertyRowMapper<>(RoomBooking.class), voyageId, RoomStatusProvider.getRoomStatusCode.get("AVAILABLE")
		);

		if (room != null) {
			jdbctemplate.update(
					"UPDATE RoomBooking SET RoomStatusCode = ? WHERE VoyageId = ? AND RoomId = ?",
					RoomStatusProvider.getRoomStatusCode.get("RESERVED"), voyageId, room.getRoomId()
			);

			return room;
		} else
			throw new RuntimeException("No available rooms");
	}

	@Override
	@Transactional
	public void bookReservedRoomByUserId(RoomBooking room, int userId, int fare) {
		assert(room != null);

		jdbctemplate.update(
				"INSERT INTO Transaction (TransactionDate, Amount, UserId) VALUE (NOW(), ?, ?)",
				fare, userId
		);

		Integer transactionId = jdbctemplate.queryForObject(
				"SELECT LAST_INSERT_ID() FROM Transaction",
				new BeanPropertyRowMapper<>(Integer.class)
		);

		if (transactionId != null) {
			jdbctemplate.update(
					"UPDATE RoomBooking SET RoomStatusCode = ?, TransactionId = ? WHERE VoyageId = ? AND RoomId = ?",
					RoomStatusProvider.getRoomStatusCode.get("BOOKED"), transactionId, room.getVoyageId(), room.getRoomId()
			);
		} else
			throw new RuntimeException("Transaction failed");
	}

}
