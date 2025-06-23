package com.cleancode.ecommerce.product.application.dto;

import java.math.BigDecimal;
import java.util.List;

import com.cleancode.ecommerce.product.domain.product.Brand;
import com.cleancode.ecommerce.product.domain.product.Description;
import com.cleancode.ecommerce.product.domain.product.Image;
import com.cleancode.ecommerce.product.domain.product.Product;
import com.cleancode.ecommerce.product.domain.product.ProductCategory;
import com.cleancode.ecommerce.product.domain.product.bag.Bag;
import com.cleancode.ecommerce.product.domain.product.bag.Color;
import com.cleancode.ecommerce.product.domain.product.bag.Volume;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;

public class CreateBagDto extends CreateProductDto {

	private String color;
	private String volume;

	public CreateBagDto(String name, String description, BigDecimal price, TypeCoin typeCoin, ProductCategory category,
			String brand, List<Image> images, String color, String volume) {
		super(name, description, price, typeCoin, ProductCategory.BAG, brand, images);
		this.color = color;
		this.volume = volume;
	}

	public String getColor() {
		return color;
	}

	public String getVolume() {
		return volume;
	}

	@Override
	public Product toProduct() {
		return new Bag(new Name(getName()), new Description(getDescription()), new Price(getPrice(), getTypeCoin()),
				getCategory(), new Brand(getBrand()), getImages(), new Volume(volume), new Color(color));
	}
}
