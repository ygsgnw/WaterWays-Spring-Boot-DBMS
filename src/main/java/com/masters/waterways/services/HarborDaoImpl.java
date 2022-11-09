package com.masters.waterways.services;

import java.util.List;

import com.masters.waterways.models.HarborStatusProvider;
import com.masters.waterways.models.VoyageStatusProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.masters.waterways.daos.HarborDao;
import com.masters.waterways.models.Harbor;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public int setSuspended(int id) {
		jdbctemplate.update(
				"UPDATE Voyage SET VoyageStatusCode = ? WHERE DepartureTime > NOW() AND (ArrivalHarborId = ? OR DepartureHarborId = ?)",
				VoyageStatusProvider.getVoyageStatusCode.get("SUSPENDED"), id, id
		);
		
		return jdbctemplate.update(
				"UPDATE Harbor SET HarborStatusCode = ? WHERE HarborId = ?",
				HarborStatusProvider.getHarborStatusCode.get("SUSPENDED"), id
		);
	}

	@Override
	public int setActive(int id) {
		return jdbctemplate.update(
				"UPDATE Harbor SET HarborStatusCode = ? WHERE HarborId = ?",
				HarborStatusProvider.getHarborStatusCode.get("OPERATIONAL"), id
		);
	}

}