package com.masters.waterways.services;

import com.masters.waterways.daos.ShipAdminViewDao;
import com.masters.waterways.models.ShipAdminView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShipAdminViewDaoImpl implements ShipAdminViewDao {

    @Autowired
    JdbcTemplate jdbctemplate;

    @Override
    public List<ShipAdminView> getAll() {
        return jdbctemplate.query(
                "SELECT * FROM ShipAdminView",
                new BeanPropertyRowMapper<>(ShipAdminView.class)
        );
    }
}
