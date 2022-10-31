package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBooking {

	private int RoomId;
	private int VoyageId;
	private int Fare;
	private int TransactionId;
	private int RoomStatus;

	public int getRoomId() {
		return RoomId;
	}

	public void setRoomId(int roomId) {
		RoomId = roomId;
	}

	public int getVoyageId() {
		return VoyageId;
	}

	public void setVoyageId(int voyageId) {
		VoyageId = voyageId;
	}

	public int getFare() {
		return Fare;
	}

	public void setFare(int fare) {
		Fare = fare;
	}

	public int getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(int transactionId) {
		TransactionId = transactionId;
	}

	public int getRoomStatus() {
		return RoomStatus;
	}

	public void setRoomStatus(int roomStatus) {
		RoomStatus = roomStatus;
	}
}
