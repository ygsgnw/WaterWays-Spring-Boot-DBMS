package com.masters.waterways.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.masters.waterways.daos.harbordao;
import com.masters.waterways.models.Harbor;

public class harbordaoimpl implements harbordao{

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int save(Harbor harbor) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO harbor (Location,ConstructionDate,ManagerId) VALUES (?,?,?)",
				new Object[] { harbor.getLocation(),harbor.getConstructionDate(),harbor.getManagerId() });
	}

	@Override
	public int update(Harbor harbor, int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE harbor SET Location=?,ManagerId=?,ConstructionDate=? WHERE HarborId=?",
				new Object[] {harbor.getLocation(),harbor.getManagerId(),harbor.getConstructionDate() },
				id);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM harbor WHERE HarborId=?",id);
	}

	@Override
	public List<Harbor> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM harbor", new BeanPropertyRowMapper<Harbor>(Harbor.class));
	}

	@Override
	public Harbor getbyid(int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM harbor WHERE HarborId=?",
				new BeanPropertyRowMapper<Harbor>(Harbor.class), id);
	}

}