package com.masters.waterways.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Harbor {

	private int HarborId;
	private String Location;
	private Date ConstructionDate;
	private int ManagerId;

	public int getHarbourId() {
		return HarborId;
	}

	public void setHarbourId(int harbourId) {
		HarborId = harbourId;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public Date getConstructionDate() {
		return ConstructionDate;
	}

	public void setConstructionDate(Date constructionDate) {
		ConstructionDate = constructionDate;
	}

	public int getManagerId() {
		return ManagerId;
	}

	public void setManagerId(int managerId) {
		ManagerId = managerId;
	}
}
