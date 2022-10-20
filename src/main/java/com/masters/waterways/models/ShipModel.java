package com.masters.waterways.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipModel {

	private long ShipModelId;
	private int RoomCount;
	private String ModelName;
	
	
	public long getShipModelId() {
		return ShipModelId;
	}
	public void setShipModelId(long shipModelId) {
		ShipModelId = shipModelId;
	}
	public int getRoomCount() {
		return RoomCount;
	}
	public void setRoomCount(int roomCount) {
		RoomCount = roomCount;
	}
	public String getModelName() {
		return ModelName;
	}
	public void setModelName(String modelName) {
		ModelName = modelName;
	}
	
	
	
}
