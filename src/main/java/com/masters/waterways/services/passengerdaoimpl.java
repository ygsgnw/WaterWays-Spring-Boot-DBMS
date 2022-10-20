package com.masters.waterways.services;
import java.util.List;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.Passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class passengerdaoimpl implements passengerdao{

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int save(Passenger passenger) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO passenger (Name,Sex,RegistrationDate,DOB,Phone) VALUES (?,?,?,?,?)",
				new Object[] {passenger.getName(),passenger.getSex(),passenger.getRegistrationDate(),passenger.getDOB(),passenger.getPhone() });
	}

	@Override
	public int update(Passenger passenger, long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE passenger SET Name=?,Sex=?,RegistrationDate=?,DOB=?,Phone=? WHERE PassengerId=?",
				new Object[] {passenger.getName(),passenger.getSex(),passenger.getRegistrationDate(),passenger.getDOB(),passenger.getPhone()},
				id);
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM Passenger WHERE PassengerId=?",id);
	}

	@Override
	public List<Passenger> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM passenger", new BeanPropertyRowMapper<Passenger>(Passenger.class));
	}

	@Override
	public Passenger getbyid(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM passenger WHERE PassengerId=?",
				new BeanPropertyRowMapper<Passenger>(Passenger.class), id);
	}

}
