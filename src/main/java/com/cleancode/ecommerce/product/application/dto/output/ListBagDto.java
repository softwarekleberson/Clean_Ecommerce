package com.cleancode.ecommerce.product.application.dto.output;

public class ListBagDto extends ListProductDto {

	private String color;
	private String volume;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
}