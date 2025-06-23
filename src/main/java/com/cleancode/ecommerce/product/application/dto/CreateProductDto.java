package com.cleancode.ecommerce.product.application.dto;

import java.math.BigDecimal;
import java.util.List;

import com.cleancode.ecommerce.product.domain.product.Image;
import com.cleancode.ecommerce.product.domain.product.ProductCategory;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;

public abstract class CreateProductDto implements ProductCreatableInterface{

	private String name;                    
	private String description;           
	private BigDecimal price;              
	private TypeCoin typeCoin;               
	private ProductCategory category;         
	private String brand;            
	private List<Image> images;  
	
	public CreateProductDto(String name, String description, BigDecimal price, TypeCoin typeCoin,
			ProductCategory category, String brand, List<Image> images) {

		this.name = name;
		this.description = description;
		this.price = price;
		this.typeCoin = typeCoin;
		this.category = category;
		this.brand = brand;
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public TypeCoin getTypeCoin() {
		return typeCoin;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public String getBrand() {
		return brand;
	}

	public List<Image> getImages() {
		return images;
	}
}