package com.masters.waterways.services;

import com.masters.waterways.daos.FoodBookingAdminViewDao;
import com.masters.waterways.models.FoodBookingAdminView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodBookingAdminViewDaoImpl implements FoodBookingAdminViewDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<FoodBookingAdminView> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM FoodBookingAdminView",
                new BeanPropertyRowMapper<>(FoodBookingAdminView.class)
        );
    }

    @Override
    public List<FoodBookingAdminView> getAllByVoyageId(int voyageId) {
        return jdbcTemplate.query(
                "SELECT * FROM FoodBookingAdminView WHERE VoyageId = ?",
                new BeanPropertyRowMapper<>(FoodBookingAdminView.class), voyageId
        );
    }

    @Override
    public List<FoodBookingAdminView> getAllByUserIdAndRoomIdAndVoyageId(int userId, int voyageId, int roomId) {
        return jdbcTemplate.query(
                "SELECT * FROM FoodBookingAdminView WHERE VoyageId = ? AND RoomId = ? AND TransactionId IN (SELECT TransactionId FROM Transaction WHERE UserId = ?)",
                new BeanPropertyRowMapper<>(FoodBookingAdminView.class), voyageId, roomId, userId
        );
    }


    @Override
    public FoodBookingAdminView getById (int id) {
        // TODO Auto-generated method stub
        return jdbcTemplate.queryForObject(
                "SELECT * FROM FoodBookingAdminView WHERE TransactionId = ?",
                new BeanPropertyRowMapper<>(FoodBookingAdminView.class), id
        );
    }
}
