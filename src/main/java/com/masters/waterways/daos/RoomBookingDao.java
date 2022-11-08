package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;
import org.springframework.transaction.annotation.Transactional;


public interface RoomBookingDao {

    RoomBooking getByTransactionId(int id);

    List<RoomBooking> getAllByVoyageId (int voyageId);

	List<RoomBooking> getAllByUserIdAndVoyageId(int userId, int voyageId);

	void bookRoomByVoyageIdAndUserId(int voyageId, int userId) throws RuntimeException;

	@Transactional(rollbackFor = Exception.class)
	RoomBooking reserveRoomByVoyageId(int voyageId);

	@Transactional(rollbackFor = Exception.class)
	void bookReservedRoomByUserId(RoomBooking room, int userId, int fare);
}
