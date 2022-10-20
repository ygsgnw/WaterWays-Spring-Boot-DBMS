package com.masters.waterways.services;
import com.masters.waterways.daos.*;
import com.masters.waterways.models.Port;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class portdaoimpl implements portdao{

	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int save(Port port) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"INSERT INTO port (Name,Status,HarborId,ManagerId,ConstructionDate) VALUES (?,?,?,?,?)",
				new Object[] { port.getName(),port.getStatus(),port.getHarbourId(),port.getManagerId(),port.getConstructionDate() });
	}

	@Override
	public int update(Port port, long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"UPDATE port SET Name=?,Status=?,HarborId=?,ManagerId=?,ConstructionDate=? WHERE PortId=?",
				new Object[] { port.getName(),port.getStatus(),port.getHarbourId(),port.getManagerId(),port.getConstructionDate() },
				id);
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update("DELETE FROM port WHERE PortId=?",id);
	}

	@Override
	public List<Port> getall() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM port", new BeanPropertyRowMapper<Port>(Port.class));
	}

	@Override
	public Port getbyid(long id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject("SELECT * FROM port WHERE PortId=?",
				new BeanPropertyRowMapper<Port>(Port.class), id);
	}

}
