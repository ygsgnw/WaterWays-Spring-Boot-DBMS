package com.masters.waterways.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voyage {
	private int VoyageId;
	private int ShipSerialId;
	private int ArrivalHarborId;
	private int DepartureHarborId;
	private LocalDateTime ArrivalTime;
	private LocalDateTime DepartureTime;
	private int VoyageStatus;


	public LocalDateTime getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		ArrivalTime = arrivalTime;
	}

	public LocalDateTime getDepartureTime() {
		return DepartureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		DepartureTime = departureTime;
	}

	public int getVoyageId() {
		return VoyageId;
	}

	public void setVoyageId(int voyageId) {
		VoyageId = voyageId;
	}

	public int getShipSerialId() {
		return ShipSerialId;
	}

	public void setShipSerialId(int shipSerialId) {
		ShipSerialId = shipSerialId;
	}

	public int getArrivalHarborId() {
		return ArrivalHarborId;
	}

	public void setArrivalHarborId(int arrivalHarbourId) {
		ArrivalHarborId = arrivalHarbourId;
	}

	public int getDepartureHarborId() {
		return DepartureHarborId;
	}

	public void setDepartureHarborId(int departureHarbourId) {
		DepartureHarborId = departureHarbourId;
	}

	

	public int getVoyageStatus() {
		return VoyageStatus;
	}

	public void setVoyageStatus(int voyageStatus) {
		VoyageStatus = voyageStatus;
	}
}
