package com.masters.waterways.models;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voyage {
	private long VoyageId;
	private long ShipSerialId;
	private long ArrivalPortId;
	private Time ArrivalTime;
	private long DeparturePortId;
	private Time DepartureTime;
	
	
	public long getVoyageId() {
		return VoyageId;
	}
	public void setVoyageId(long voyageId) {
		VoyageId = voyageId;
	}
	public long getShipSerialId() {
		return ShipSerialId;
	}
	public void setShipSerialId(long shipSerialId) {
		ShipSerialId = shipSerialId;
	}
	public long getArrivalPortId() {
		return ArrivalPortId;
	}
	public void setArrivalPortId(long arrivalPortId) {
		ArrivalPortId = arrivalPortId;
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
	public long getDeparturePortId() {
		return DeparturePortId;
	}
	public void setDeparturePortId(long departurePortId) {
		DeparturePortId = departurePortId;
	}
	
	
}
