package com.cleancode.ecommerce.product.application.dto.output;

public class ListBagDto extends ListProductDto {

	private String color;
	private double volume;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
}