package com.masters.waterways.services;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.RoomStatusProvider;
import com.masters.waterways.models.ShipModel;
import com.masters.waterways.models.Voyage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class VoyageDaoImpl implements VoyageDao {

	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	@Transactional
	public void insert (Voyage voyage) {
		// TODO Auto-generated method stub
		jdbctemplate.update(
				"INSERT INTO Voyage (ShipSerialId, Fare, ArrivalHarborId, ArrivalTime, DepartureHarborId, DepartureTime, VoyageStatusCode) VALUES (?, ?, ?, ?, ?, ?, ?)",
				voyage.getShipSerialId(), voyage.getFare(), voyage.getArrivalHarborId(), voyage.getArrivalTime(), voyage.getDepartureHarborId(), voyage.getDepartureTime(), voyage.getVoyageStatusCode()
		);

//		ShipModel shipModel = jdbctemplate.queryForObject(
//				"SELECT * FROM ShipModel WHERE ModelId = (SELECT ModelId FROM Ship WHERE ShipSerialId = ?)",
//				new BeanPropertyRowMapper<>(ShipModel.class), voyage.getShipSerialId()
//		);
//
//		if (shipModel == null)
//			throw new RuntimeException("Voyage insertion trigger failed");
//
//		for (int i = 1; i <= shipModel.getRoomCount(); i++)
//			jdbctemplate.update(
//					"INSERT INTO RoomBooking (TransactionId, RoomId, VoyageId, RoomStatusCode) VALUES (null, ?, (SELECT LAST_INSERT_ID()), ?)",
//					i, RoomStatusProvider.getRoomStatusCode.get("AVAILABLE")
//			);
	}

	@Override
	public int delete (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"DELETE FROM Voyage WHERE VoyageId = ?",
				id
		);
	}

	@Override
	public List<Voyage> getAll () {
//		System.out.println("Hi");
		// TODO Auto-generated method stub
		return jdbctemplate.query(
				"SELECT * FROM Voyage",
				new BeanPropertyRowMapper<>(Voyage.class)
		);
	}

	@Override
	public Voyage getById (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject(
				"SELECT * FROM Voyage WHERE VoyageId = ?",
				new BeanPropertyRowMapper<>(Voyage.class), id
		);
	}

	@Override
	public List<Voyage> getAllActive () {
		return jdbctemplate.query(
				"SELECT * FROM Voyage WHERE DepartureTime > NOW()",
				new BeanPropertyRowMapper<>(Voyage.class)
		);
	}

    @Override
    public List<Voyage> getAllActiveByUserId(int userId) {
		return jdbctemplate.query(
				"SELECT * FROM Voyage WHERE VoyageId IN (" +
						"SELECT VoyageId FROM RoomBooking, Transaction " +
						"WHERE RoomBooking.TransactionId = Transaction.TransactionId " +
						"AND Transaction.UserId = ? AND DepartureTime > NOW())",
				new BeanPropertyRowMapper<>(Voyage.class), userId
		);
    }

	@Override
	public List<Voyage> getAllCompletedByUserId(int userId) {
		return jdbctemplate.query(
				"SELECT * FROM Voyage WHERE VoyageId IN (" +
						"SELECT VoyageId FROM RoomBooking, Transaction " +
						"WHERE RoomBooking.TransactionId = Transaction.TransactionId " +
						"AND Transaction.UserId = ? AND DepartureTime < NOW())",
				new BeanPropertyRowMapper<>(Voyage.class), userId
		);
	}

	@Override
	public void setSuspended (int voyageId) {
		jdbctemplate.update(
				"insert into Transaction (TransactionDate, Amount, UserId) (" +
					"select now(), -sum(Transaction.Amount), Transaction.UserId " +
					"from Transaction, RoomBooking where RoomBooking.TransactionId = Transaction.TransactionId and RoomBooking.VoyageId = ? " +
					"group by Transaction.UserId)", voyageId
		);

		jdbctemplate.update(
				"insert into Transaction (TransactionDate, Amount, UserId) (" +
					"select now(), -sum(Transaction.Amount), Transaction.UserId " +
					"from Transaction, FoodBooking where Transaction.TransactionId = FoodBooking.TransactionId and FoodBooking.VoyageId = ? " +
					"group by Transaction.UserId)", voyageId
		);
	}

	@Override
	public void updateVoyageByFare(int voyageId, int fare) {

	}

	@Override
	public boolean isVoyageCompletedByVoyageId(int voyageId) {
		return jdbctemplate.query(
				"SELECT * FROM Voyage WHERE VoyageId = ? AND DepartureTime > NOW()",
				new BeanPropertyRowMapper<>(Voyage.class), voyageId
		).isEmpty();
	}
}
