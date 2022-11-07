package com.masters.waterways.services;

import java.util.List;

import com.masters.waterways.models.HarborStatusProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.masters.waterways.daos.HarborDao;
import com.masters.waterways.models.Harbor;

@Repository
public class HarborDaoImpl implements HarborDao {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int insert (Harbor harbor) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO Harbor (Location, ConstructionDate, ManagerId, HarborStatusCode) VALUES (?, ?, ?, ?)",
				harbor.getLocation(), harbor.getConstructionDate(), harbor.getManagerId(), harbor.getHarborStatusCode()
		);
	}

	@Override
	public int update (Harbor harbor, int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE Harbor SET Location=?,ManagerId=?,ConstructionDate=? WHERE HarborId=?",
				harbor.getLocation(), harbor.getManagerId(), harbor.getConstructionDate(), id
		);
	}

	@Override
	public int delete (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"DELETE FROM Harbor WHERE HarborId = ?", id
		);
	}

	@Override
	public List<Harbor> getAll () {
		// TODO Auto-generated method stub
		return jdbctemplate.query(
				"SELECT * FROM Harbor", new BeanPropertyRowMapper<Harbor>(Harbor.class)
		);
	}

	@Override
	public Harbor getById (int id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject(
				"SELECT * FROM Harbor WHERE HarborId = ?",
				new BeanPropertyRowMapper<Harbor>(Harbor.class), id
		);
	}

	@Override
	public void setSuspended(int id) {

	}

	@Override
	public void setActive(int id) {
		jdbctemplate.update(
				"UPDATE Harbor SET HarborStatusCode = ? WHERE HarborId = ?",
				HarborStatusProvider.getHarborStatusCode.get("ACTIVE"), id
		);
	}

}