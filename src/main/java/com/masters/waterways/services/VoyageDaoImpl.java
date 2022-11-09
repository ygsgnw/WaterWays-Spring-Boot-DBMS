package com.masters.waterways.services;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
	public int insert (Voyage voyage) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
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
		System.out.println(id);

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
	public int setSuspended (int voyageId) {



//		jdbctemplate.update(
//				"insert into Transaction (TransactionDate, Amount, UserId) (" +
//					"select now(), -sum(Transaction.Amount), Transaction.UserId " +
//					"from Transaction, RoomBooking where RoomBooking.TransactionId = Transaction.TransactionId and RoomBooking.VoyageId = ? " +
//					"group by Transaction.UserId)", voyageId
//		);
//
//		jdbctemplate.update(
//				"insert into Transaction (TransactionDate, Amount, UserId) (" +
//					"select now(), -sum(Transaction.Amount), Transaction.UserId " +
//					"from Transaction, FoodBooking where Transaction.TransactionId = FoodBooking.TransactionId and FoodBooking.VoyageId = ? " +
//					"group by Transaction.UserId)", voyageId
//		);

		return jdbctemplate.update(
				"UPDATE Voyage SET VoyageStatusCode = ? WHERE VoyageId = ?",
				VoyageStatusProvider.getVoyageStatusCode.get("SUSPENDED"), voyageId
		);
	}

	@Override
	public int setOperational(int voyageId) {

		Voyage voyage = getById(voyageId);

		if (jdbctemplate.query(
				"SELECT * FROM Ship WHERE ShipSerialId = ? AND ShipStatusCode = ?",
				new BeanPropertyRowMapper<>(Ship.class), voyage.getShipSerialId(), ShipStatusProvider.getShipStatusCode.get("SUSPENDED")
		).isEmpty()
			&&
			jdbctemplate.query(
					"SELECT * FROM Harbor WHERE (HarborId = ? OR HarborId = ?) AND HarborStatusCode = ?",
					new BeanPropertyRowMapper<>(Harbor.class), voyage.getDepartureHarborId(), voyage.getArrivalHarborId(), HarborStatusProvider.getHarborStatusCode.get("SUSPENDED")
			).isEmpty()
		)
			return jdbctemplate.update(
					"UPDATE Voyage SET VoyageStatusCode = ? WHERE VoyageId = ?",
					VoyageStatusProvider.getVoyageStatusCode.get("OPERATIONAL"), voyageId
			);
		else
			return 0;
	}

	@Override
	public int updateVoyageByFare(int voyageId, int fare) {
		return jdbctemplate.update(
				"UPDATE Voyage SET Fare = ? WHERE VoyageId = ?",
				fare, voyageId
		);
	}

	@Override
	public boolean isVoyageCompletedByVoyageId(int voyageId) {
		return jdbctemplate.query(
				"SELECT * FROM Voyage WHERE VoyageId = ? AND DepartureTime > NOW()",
				new BeanPropertyRowMapper<>(Voyage.class), voyageId
		).isEmpty();
	}
}
