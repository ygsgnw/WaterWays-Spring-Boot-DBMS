package com.masters.waterways.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.masters.waterways.daos.UserDao;
import com.masters.waterways.models.Users;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int insert (Users user) {
		return jdbctemplate.update(
				"INSERT INTO Users (UserId, UserName, EmailId, UserPassword) VALUES (?, ?, ?, ?)",
				user.getUserId(), user.getName(), user.getEmailId(), user.getUserPassword()
		);
	}

	@Override
	public int update (Users user, int id) {
		return jdbctemplate.update(
				"UPDATE Users SET UserName = ?, EmailId = ?, UserPassword = ? WHERE UserId = ?",
				user.getUserId(), user.getName(), user.getEmailId(), user.getUserPassword(), id
		);
	}

	@Override
	public int delete (int id) {
		return jdbctemplate.update(
				"DELETE FROM Users WHERE UserId = ?", id
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
	public Users getById (int id) {
		return jdbctemplate.queryForObject(
				"SELECT * FROM Users WHERE UserId = ?",
				new BeanPropertyRowMapper<Users>(Users.class), id
		);
	}
}
