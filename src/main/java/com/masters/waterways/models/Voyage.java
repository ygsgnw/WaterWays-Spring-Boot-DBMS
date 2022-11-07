package com.masters.waterways.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voyage {

	private int VoyageId;
	private int ShipSerialId;
	private int Fare;
	private int DepartureHarborId;
	private int ArrivalHarborId;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime DepartureTime;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime ArrivalTime;
	private int VoyageStatusCode;

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

	public int getFare() {
		return Fare;
	}

	public void setFare(int fare) {
		Fare = fare;
	}

	public int getArrivalHarborId() {
		return ArrivalHarborId;
	}

	public void setArrivalHarborId(int arrivalHarborId) {
		ArrivalHarborId = arrivalHarborId;
	}

	public int getDepartureHarborId() {
		return DepartureHarborId;
	}

	public void setDepartureHarborId(int departureHarborId) {
		DepartureHarborId = departureHarborId;
	}

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

	public int getVoyageStatusCode() {
		return VoyageStatusCode;
	}

	public void setVoyageStatusCode(int voyageStatus) {
		VoyageStatusCode = voyageStatus;
	}
}
