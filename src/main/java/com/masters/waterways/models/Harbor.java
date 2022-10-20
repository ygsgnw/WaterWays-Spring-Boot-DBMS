package com.masters.waterways.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Harbor {

	private long HarborId;
	private String name;
	private String Location;
	private long ManagerId;
	private Date ConstructionDate;
	
	public long getHarborId() {
		return HarborId;
	}
	public void setHarborId(long harborId) {
		HarborId = harborId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
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
