package com.masters.waterways.services;
import com.masters.waterways.daos.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class roomtypedaoimpl implements roomtypedao{

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int save(RoomType roomtype) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO roomtype (Fare,ShipSerialId,Status,Images,Description) VALUES (?,?,?,?,?)",
				new Object[] { roomtype.getFare(),roomtype.getShipSerialId(),roomtype.getStatus(),roomtype.getImages(),roomtype.getDescription() });
	}

	@Override
	public int update(RoomType roomtype, long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE roomtype SET Fare=?,ShipSerialId=?,Status=?,Images=?,Description=? WHERE RoomTypeId=?",
				new Object[] { roomtype.getFare(),roomtype.getShipSerialId(),roomtype.getStatus(),roomtype.getImages(),roomtype.getDescription() },
				id);
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM roomtype WHERE RoomTypeId=?",id);
	}

	@Override
	public List<RoomType> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM roomtype", new BeanPropertyRowMapper<RoomType>(RoomType.class));
	}

	@Override
	public RoomType getbyid(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM roomtype WHERE RoomTypeId=?",
				new BeanPropertyRowMapper<RoomType>(RoomType.class), id);
	}

}
