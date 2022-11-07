package com.masters.waterways.models;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ship {

	private int ShipSerialId;
	private int ModelId;
	private int ShipStatusCode;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime MfDate;

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

	public int getShipStatusCode() {
		return ShipStatusCode;
	}

	public void setShipStatusCode(int shipStatusCode) {
		ShipStatusCode = shipStatusCode;
	}

	public LocalDateTime getMfDate() {
		return MfDate;
	}

	public void setMfDate(LocalDateTime mfDate) {
		MfDate = mfDate;
	}
}
