package com.masters.waterways.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
	private long PassengerId;
	private String Name;
	private int Sex;
	private Date RegistrationDate;
	private Date DOB;
	private long Phone;
	
	
	public long getPassengerId() {
		return PassengerId;
	}
	public void setPassengerId(long passengerId) {
		PassengerId = passengerId;
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
	public Date getRegistrationDate() {
		return RegistrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		RegistrationDate = registrationDate;
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
		this.Phone = phone;
	}
	
}
