package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;
import org.springframework.transaction.annotation.Transactional;


public interface RoomBookingDao {

	int insert(RoomBooking room_booking);

	int update(RoomBooking room_booking);

	int delete(int id);

	List<RoomBooking> getAll();

	RoomBooking getById(int id);

	List<RoomBooking> getRoomsByUserIdAndVoyageId(int userId, int voyageId);

	void roomBookingByVoyageIdAndUserId(int voyageId, int userId) throws RuntimeException;

	@Transactional(rollbackFor = Exception.class)
	RoomBooking reserveRoomByVoyageId(int voyageId);

	@Transactional(rollbackFor = Exception.class)
	void roomBookingByReservedRoomIdAndUserId(RoomBooking room, int userId, int fare);
}
