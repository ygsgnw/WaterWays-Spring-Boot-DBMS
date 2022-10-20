package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBooking {
	private long RoomBookingId;
	private long VoyageId;
	private long TransactionId;
	private long PassengerId;
	private String Status;
	private int RoomId;
	
	
	public long getRoomBookingId() {
		return RoomBookingId;
	}
	public void setRoomBookingId(long roomBookingId) {
		RoomBookingId = roomBookingId;
	}
	public long getVoyageId() {
		return VoyageId;
	}
	public void setVoyageId(long voyageId) {
		VoyageId = voyageId;
	}
	public long getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(long transactionId) {
		TransactionId = transactionId;
	}
	public long getPassengerId() {
		return PassengerId;
	}
	public void setPassengerId(long passengerId) {
		PassengerId = passengerId;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getRoomId() {
		return RoomId;
	}
	public void setRoomId(int roomId) {
		RoomId = roomId;
	}
	
}
