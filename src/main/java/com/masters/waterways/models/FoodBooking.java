package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodBooking {
	private long FoodBookingId;
	private long TypeId;
	private long VoyageId;
	private long TransactionId;
	private long passengerId;
	private String status;
	
	public long getFoodBookingId() {
		return FoodBookingId;
	}
	public void setFoodBookingId(long foodBookingId) {
		FoodBookingId = foodBookingId;
	}
	public long getTypeId() {
		return TypeId;
	}
	public void setTypeId(long typeId) {
		TypeId = typeId;
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
		return passengerId;
	}
	public void setPassengerId(long passengerId) {
		this.passengerId = passengerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
