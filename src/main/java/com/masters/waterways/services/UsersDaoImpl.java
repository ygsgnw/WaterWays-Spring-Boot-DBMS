package com.masters.waterways.services;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.masters.waterways.daos.UsersDao;
import com.masters.waterways.models.Users;

@Repository
public class UsersDaoImpl implements UsersDao {
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int insert (Users user) {
		return jdbctemplate.update(
				"INSERT INTO Users (UserId, UserName, EmailId, UserPassword) VALUES (?, ?, ?, ?)",
				user.getUserId(), user.getUserName(), user.getEmailId(), user.getUserPassword()
		);
	}

	@Override
	public int update (Users user) {
		return jdbctemplate.update(
				"UPDATE Users SET UserName = ?, EmailId = ?, UserPassword = ? WHERE UserId = ?",
				user.getUserName(), user.getEmailId(), user.getUserPassword(), user.getUserId()
		);
	}

	@Override
	public List<Users> getAll () {
		return jdbctemplate.query(
				"SELECT * FROM Users",
				new BeanPropertyRowMapper<Users>(Users.class)
		);

	}

    @Override
    public List<Users> getAllNonEmployees() {
		return jdbctemplate.query(
				"SELECT * FROM Users WHERE UserId NOT IN (SELECT UserId FROM Employee)",
				new BeanPropertyRowMapper<>(Users.class)
		);
    }

    @Override
	public Users getById (int id) {
		return jdbctemplate.queryForObject(
				"SELECT * FROM Users WHERE UserId = ?",
				new BeanPropertyRowMapper<Users>(Users.class), id
		);
	}

	@Override
	public Users getByEmailId(String emailId) {
		return jdbctemplate.queryForObject("SELECT * FROM Users WHERE EmailId=?;",new BeanPropertyRowMapper<Users>(Users.class),emailId);
	}
}
