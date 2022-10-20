package com.masters.waterways.services;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.Voyage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class voyagedaoimpl implements voyagedao{

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int save(Voyage voyage) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO voyage (ShipSerialId,ArrivalPortId,ArrivalTime,DeparturePortId,DepartureTime) VALUES (?,?,?,?,?)",
				new Object[] { voyage.getShipSerialId(),voyage.getArrivalPortId(),voyage.getArrivalTime(),voyage.getDeparturePortId(),voyage.getDepartureTime() });
	}

	@Override
	public int update(Voyage voyage, long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE voyage SET ShipSerialId=?,ArrivalPortId=?,ArrivalTime=?,DeparturePortId=?,DepartureTime=? WHERE Id=?",
				new Object[] { voyage.getShipSerialId(),voyage.getArrivalPortId(),voyage.getArrivalTime(),voyage.getDeparturePortId(),voyage.getDepartureTime() },
				id);
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM voyage WHERE VoyageId=?",id);
	}

	@Override
	public List<Voyage> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM voyage", new BeanPropertyRowMapper<Voyage>(Voyage.class));
	}

	@Override
	public Voyage getbyid(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM voyage WHERE VoyageId=?",
				new BeanPropertyRowMapper<Voyage>(Voyage.class), id);
	}

}
