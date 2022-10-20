package com.masters.waterways.models;


public class RoomType {
	private int RoomId;
	private String Fare;
	private long ShipSerialId;
	private String Status;
	private String Images;
	private String Description;
	
	
	public int getRoomId() {
		return RoomId;
	}
	public void setRoomId(int roomId) {
		RoomId = roomId;
	}
	public String getFare() {
		return Fare;
	}
	public void setFare(String fare) {
		Fare = fare;
	}
	public long getShipSerialId() {
		return ShipSerialId;
	}
	public void setShipSerialId(long shipSerialId) {
		ShipSerialId = shipSerialId;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getImages() {
		return Images;
	}
	public void setImages(String images) {
		Images = images;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
