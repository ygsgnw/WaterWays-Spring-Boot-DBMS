package com.masters.waterways.services;

import com.masters.waterways.daos.FoodBookingViewDao;
import com.masters.waterways.models.FoodBookingView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodBookingViewDaoImpl implements FoodBookingViewDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<FoodBookingView> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM FoodBookingAdminView",
                new BeanPropertyRowMapper<>(FoodBookingView.class)
        );
    }

    @Override
    public List<FoodBookingView> getAllByVoyageId(int voyageId) {
        return jdbcTemplate.query(
                "SELECT * FROM FoodBookingAdminView WHERE VoyageId = ?",
                new BeanPropertyRowMapper<>(FoodBookingView.class), voyageId
        );
    }

    @Override
    public List<FoodBookingView> getAllByRoomIdAndVoyageId(int voyageId, int roomId) {
        return jdbcTemplate.query(
                "SELECT * FROM FoodBookingAdminView WHERE VoyageId = ? AND RoomId = ?",
                new BeanPropertyRowMapper<>(FoodBookingView.class), voyageId, roomId
        );
    }

    @Override
    public FoodBookingView getById(int id) {
        // TODO Auto-generated method stub
        return jdbcTemplate.queryForObject(
                "SELECT * FROM FoodBookingAdminView WHERE TransactionId = ?",
                new BeanPropertyRowMapper<>(FoodBookingView.class), id
        );
    }
}
