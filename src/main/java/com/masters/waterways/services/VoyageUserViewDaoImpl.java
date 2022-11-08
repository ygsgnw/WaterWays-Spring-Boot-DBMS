package com.masters.waterways.services;

import com.masters.waterways.daos.VoyageUserViewDao;
import com.masters.waterways.models.VoyageUserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoyageUserViewDaoImpl implements VoyageUserViewDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<VoyageUserView> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM VoyageUserView",
                new BeanPropertyRowMapper<>(VoyageUserView.class)
        );
    }

    @Override
    public VoyageUserView getById(int id) {
        // TODO Auto-generated method stub
        return jdbcTemplate.queryForObject(
                "SELECT * FROM VoyageUserView WHERE VoyageId = ?",
                new BeanPropertyRowMapper<>(VoyageUserView.class), id
        );
    }

    @Override
    public List<VoyageUserView> getAllActive () {
        return jdbcTemplate.query(
                "SELECT * FROM VoyageUserView WHERE DepartureTime > NOW()",
                new BeanPropertyRowMapper<>(VoyageUserView.class)
        );
    }

    @Override
    public List<VoyageUserView> getAllActiveByUserId(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM VoyageUserView WHERE VoyageId IN (" +
                        "SELECT VoyageId FROM RoomBooking, Transaction " +
                        "WHERE RoomBooking.TransactionId = Transaction.TransactionId " +
                        "AND Transaction.UserId = ? AND DepartureTime > NOW())",
                new BeanPropertyRowMapper<>(VoyageUserView.class), userId
        );
    }

    @Override
    public List<VoyageUserView> getAllCompletedByUserId(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM VoyageUserView WHERE VoyageId IN (" +
                        "SELECT VoyageId FROM RoomBooking, Transaction " +
                        "WHERE RoomBooking.TransactionId = Transaction.TransactionId " +
                        "AND Transaction.UserId = ? AND DepartureTime < NOW())",
                new BeanPropertyRowMapper<>(VoyageUserView.class), userId
        );
    }
}
