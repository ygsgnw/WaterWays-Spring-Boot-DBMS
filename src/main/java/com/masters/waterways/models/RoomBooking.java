package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBooking {

	private int TransactionId;
	private int RoomId;
	private int VoyageId;
	private int RoomStatusCode;

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

	public int getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(int transactionId) {
		TransactionId = transactionId;
	}

	public int getRoomStatusCode() {
		return RoomStatusCode;
	}

	public void setRoomStatusCode(int roomStatus) {
		RoomStatusCode = roomStatus;
	}
}
