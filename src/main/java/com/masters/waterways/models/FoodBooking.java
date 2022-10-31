package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodBooking {

	private int FoodItemId;
	private int VoyageId;
	private int FoodItemCount;
	private int TransactionId;

	public int getFoodItemId() {
		return FoodItemId;
	}

	public void setFoodItemId(int foodItemId) {
		FoodItemId = foodItemId;
	}

	public int getVoyageId() {
		return VoyageId;
	}

	public void setVoyageId(int voyageId) {
		VoyageId = voyageId;
	}

	public int getFoodItemCount() {
		return FoodItemCount;
	}

	public void setFoodItemCount(int foodItemCount) {
		FoodItemCount = foodItemCount;
	}

	public int getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(int transactionId) {
		TransactionId = transactionId;
	}
}
