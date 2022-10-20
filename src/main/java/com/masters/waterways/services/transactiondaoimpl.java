package com.masters.waterways.services;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.Transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class transactiondaoimpl implements transactiondao{

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int save(Transaction transaction) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO transaction (Amount,PassengerId) VALUES (?,?)",
				new Object[] {  transaction.getAmount(),transaction.getPassengerId()});
	}

	@Override
	public int update(Transaction transaction, long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE transaction SET Amount,PassengerId=? WHERE TransactionId=?",
				new Object[] { transaction.getAmount(),transaction.getPassengerId() },
				id);
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM transaction WHERE TransactionId=?",id);
	}

	@Override
	public List<Transaction> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM transaction", new BeanPropertyRowMapper<Transaction>(Transaction.class));
	}

	@Override
	public Transaction getbyid(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM transaction WHERE TransactionId=?",
				new BeanPropertyRowMapper<Transaction>(Transaction.class), id);
	}

}
