package com.masters.waterways.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ship {

	private int ShipSerialId;
	private int ModelId;
	private int ShipStatus;
	private Date MfDate;

	public int getShipSerialId() {
		return ShipSerialId;
	}

	public void setShipSerialId(int shipSerialId) {
		ShipSerialId = shipSerialId;
	}

	public int getModelId() {
		return ModelId;
	}

	public void setModelId(int modelId) {
		ModelId = modelId;
	}

	public int getShipStatus() {
		return ShipStatus;
	}

	public void setShipStatus(int shipStatus) {
		ShipStatus = shipStatus;
	}

	public Date getMfDate() {
		return MfDate;
	}

	public void setMfDate(Date mfDate) {
		MfDate = mfDate;
	}
}
