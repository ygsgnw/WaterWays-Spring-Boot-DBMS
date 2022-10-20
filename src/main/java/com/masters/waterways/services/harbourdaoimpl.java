package com.masters.waterways.services;

import com.masters.waterways.daos.*;
import com.masters.waterways.models.Harbor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class harbourdaoimpl implements harbourdao{

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int save(Harbor harbor) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO Employee (name,Location,ManagerId,ConstructionDate) VALUES (?,?,?,?)",
				new Object[] { harbor.getName(),harbor.getLocation(),harbor.getManagerId(),harbor.getConstructionDate() });
	}

	@Override
	public int update(Harbor harbor, long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE harbor SET name=?,Location=?,ManagerId=?,ConstructionDate=? WHERE HarborId=?",
				new Object[] { harbor.getName(),harbor.getLocation(),harbor.getManagerId(),harbor.getConstructionDate() },
				id);
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM Harbor WHERE HarborId=?",id);
	}

	@Override
	public List<Harbor> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM harbor", new BeanPropertyRowMapper<Harbor>(Harbor.class));
	}

	@Override
	public Harbor getbyid(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM harbor WHERE HarborId=?",
				new BeanPropertyRowMapper<Harbor>(Harbor.class), id);
	}

}
