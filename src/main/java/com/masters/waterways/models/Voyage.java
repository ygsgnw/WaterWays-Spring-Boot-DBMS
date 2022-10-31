package com.masters.waterways.models;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voyage {
	private int VoyageId;
	private int ShipSerialId;
	private int ArrivalHarbourId;
	private int DepartureHarbourId;
	private Time ArrivalTime;
	private Time DepartureTime;
	private int VoyageStatus;


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

	public int getArrivalHarbourId() {
		return ArrivalHarbourId;
	}

	public void setArrivalHarbourId(int arrivalHarbourId) {
		ArrivalHarbourId = arrivalHarbourId;
	}

	public int getDepartureHarbourId() {
		return DepartureHarbourId;
	}

	public void setDepartureHarbourId(int departureHarbourId) {
		DepartureHarbourId = departureHarbourId;
	}

	public Time getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		ArrivalTime = arrivalTime;
	}

	public Time getDepartureTime() {
		return DepartureTime;
	}

	public void setDepartureTime(Time departureTime) {
		DepartureTime = departureTime;
	}

	public int getVoyageStatus() {
		return VoyageStatus;
	}

	public void setVoyageStatus(int voyageStatus) {
		VoyageStatus = voyageStatus;
	}
}
