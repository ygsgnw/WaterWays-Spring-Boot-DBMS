package com.masters.waterways.models;

import java.sql.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private long EmployeeId;
	private String Name;
	private int Sex;
	private int ManagerId;
	private Date DOB;
	private long Phone;
	private Date DateofJining;
	private String email;
	
	public long getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(long employeeId) {
		EmployeeId = employeeId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getSex() {
		return Sex;
	}
	public void setSex(int sex) {
		Sex = sex;
	}
	public int getManagerId() {
		return ManagerId;
	}
	public void setManagerId(int managerId) {
		ManagerId = managerId;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public long getPhone() {
		return Phone;
	}
	public void setPhone(long phone) {
		Phone = phone;
	}
	public Date getDateofJining() {
		return DateofJining;
	}
	public void setDateofJining(Date dateofJining) {
		DateofJining = dateofJining;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
//	public Employee(long id,String name,int s,int mid,Date d,long p) {
//		this.empid = id;
//		this.DOB = d;
//		this.Ename = name;
//		this.sex = s;
//		this.phone = p;
//		this.managerId = mid;
//	}
//	
	
}
