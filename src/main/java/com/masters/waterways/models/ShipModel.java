package com.masters.waterways.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipModel {

	private int ModelId;
	private int RoomCount;
	private String ModelName;

	public int getModelId() {
		return ModelId;
	}

	public void setModelId(int modelId) {
		ModelId = modelId;
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
