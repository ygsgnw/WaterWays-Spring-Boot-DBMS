package com.masters.waterways.services;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.masters.waterways.daos.FoodItemDao;
import com.masters.waterways.models.FoodItem;

@Repository
public class FoodItemDaoImpl implements FoodItemDao {

	JdbcTemplate jdbctemplate;

	@Override
	public int insert (FoodItem fi) {
		return jdbctemplate.update(
				"INSERT INTO FoodItem (VoyageId, FoodItemId, FoodName, FoodCost, FoodDescription) VALUES (?, ?, ?, ?, ?)",
				fi.getVoyageId(), fi.getFoodItemId(), fi.getFoodName(), fi.getFoodCost(), fi.getFoodDescription()
		);
	}

	@Override
	public void delete(FoodItem foodItem) throws RuntimeException {
		if (jdbctemplate.query(
				"SELECT VoyageId FROM Voyage WHERE VoyageId = ? AND DepartureTime < NOW()",
				new BeanPropertyRowMapper<>(Integer.class), foodItem.getVoyageId()
		).isEmpty()) {
			jdbctemplate.update(
					"insert into Transaction (TransactionDate, Amount, UserId) (" +
						"select now(), -sum(Transaction.amount), Transaction.UserId " +
						"from Transaction, FoodBooking where FoodBooking.TransactionId = Transaction.TransactionId and FoodBooking.FoodItemId = @FoodItemId and FoodBooking.VoyageId = @VoyageId " +
						"group by UserId);"
			);
			jdbctemplate.update(
					"DELETE FROM FoodItem WHERE FoodItemId = ? AND VoyageId = ?",
					foodItem.getFoodItemId(), foodItem.getVoyageId()
			);
		} else {
			throw new RuntimeException("Cannot delete FoodItem that was served on a completed voyage");
		}
	}

	@Override
	public List<FoodItem> getAll () {
		return jdbctemplate.query(
				"SELECT * FROM FoodItem",
				new BeanPropertyRowMapper<>(FoodItem.class)
		);

	}

	@Override
	public FoodItem getById (int id) {
		return jdbctemplate.queryForObject(
				"SELECT * FROM FoodItem WHERE FoodItemId = ?",
				new BeanPropertyRowMapper<>(FoodItem.class), id
		);
	}

	@Override
	public List<FoodItem> getFoodItemsByVoyageId (int voyageId) {
		return jdbctemplate.query(
				"SELECT * FROM FoodItem WHERE VoyageId = ?",
				new BeanPropertyRowMapper<>(FoodItem.class), voyageId
		);
	}

}
