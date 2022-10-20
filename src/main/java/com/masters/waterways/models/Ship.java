package com.masters.waterways.models;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ship {

	private long ShipId;
	private long ModelId;
	private String Name;
	private String Images;
	private String status;
	private Date ManufacturingDate;
	
	
	public long getShipId() {
		return ShipId;
	}
	public void setShipId(long shipId) {
		ShipId = shipId;
	}
	public long getModelId() {
		return ModelId;
	}
	public void setModelId(long modelId) {
		ModelId = modelId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getImages() {
		return Images;
	}
	public void setImages(String images) {
		Images = images;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getManufacturingDate() {
		return ManufacturingDate;
	}
	public void setManufacturingDate(Date manufacturingDate) {
		ManufacturingDate = manufacturingDate;
	}
	
	
	
	
	
}
