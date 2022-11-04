package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private long EmployeeId;
	private int UserId;
	private LocalDateTime JoinDate;

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

	public LocalDateTime getJoinDate() {
		return JoinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		JoinDate = joinDate;
	}
}