package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private long EmployeeId;
	private int UserId;
	private int ManagerId;

	public long getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(long employeeId) {
		EmployeeId = employeeId;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getManagerId() {
		return ManagerId;
	}

	public void setManagerId(int managerId) {
		ManagerId = managerId;
	}
}
