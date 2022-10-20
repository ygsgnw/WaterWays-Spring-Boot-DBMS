package com.masters.waterways.models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodType {

	private long FoodTypeId;
	private String Name;
	private int Cost;
	private String Image;
	private String Description;
	
	
	public long getFoodTypeId() {
		return FoodTypeId;
	}
	public void setFoodTypeId(long foodTypeId) {
		FoodTypeId = foodTypeId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getCost() {
		return Cost;
	}
	public void setCost(int cost) {
		Cost = cost;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		this.Image = image;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
