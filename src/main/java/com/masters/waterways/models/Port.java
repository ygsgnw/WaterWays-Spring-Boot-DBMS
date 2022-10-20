package com.masters.waterways.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Port {

	private long PortId;
	private String Name;
	private String Status;
	private long HarbourId;
	private long ManagerId;
	private Date ConstructionDate;
	
	
	public long getPortId() {
		return PortId;
	}
	public void setPortId(long portId) {
		PortId = portId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public long getHarbourId() {
		return HarbourId;
	}
	public void setHarbourId(long harbourId) {
		HarbourId = harbourId;
	}
	public long getManagerId() {
		return ManagerId;
	}
	public void setManagerId(long managerId) {
		ManagerId = managerId;
	}
	public Date getConstructionDate() {
		return ConstructionDate;
	}
	public void setConstructionDate(Date constructionDate) {
		ConstructionDate = constructionDate;
	}
	
}
