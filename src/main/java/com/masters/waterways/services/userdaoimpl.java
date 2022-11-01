package com.masters.waterways.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.masters.waterways.daos.userdao;
import com.masters.waterways.models.Users;

@Repository
public class userdaoimpl implements userdao{
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int save(Users users) {
		return jdbctemplate.update(
				"INSERT INTO users (UserId,Name,EmailId,UserPassword) VALUES (?,?,?,?)",
				new Object[] {users.getUserId(),users.getName(),users.getEmailId(),users.getUserPassword()});
	}

	@Override
	public int update(Users users, int id) {
		return jdbctemplate.update(
				"UPDATE crew SET Name=?,EmailId=?,UserPassword=? WHERE UserId=?",
				new Object[] { users.getUserId(),users.getName(),users.getEmailId(),users.getUserPassword() },
				id);
	}

	@Override
	public int delete(int id) {
		return jdbctemplate.update("DELETE FROM users WHERE UserId=?",id);
	}

	@Override
	public List<Users> getall() {
		return jdbctemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<Users>( Users.class));

	}

	@Override
	public Users getbyid(int id) {
		return jdbctemplate.queryForObject("SELECT * FROM users WHERE Id=?",
				new BeanPropertyRowMapper<Users>( Users.class), id);
	}
}
