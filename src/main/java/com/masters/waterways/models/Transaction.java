package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	private long TransactionId;
	private int Amount;
	private long PassengerId;
	
	
	public long getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(long transactionId) {
		TransactionId = transactionId;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public long getPassengerId() {
		return PassengerId;
	}
	public void setPassengerId(long passengerId) {
		this.PassengerId = passengerId;
	}
	
}
