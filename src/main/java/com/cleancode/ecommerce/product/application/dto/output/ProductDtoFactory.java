package com.cleancode.ecommerce.product.application.dto.output;

import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.bag.Bag;

public class ProductDtoFactory {

	public static ListProductDto listAllProduct(Product product) {

		if (product instanceof Bag bag) {
			ListBagDto dto = new ListBagDto();
			dto.setId(bag.getIdProduct().getIdProduct());
			dto.setName(bag.getName().getName());
			dto.setDescription(bag.getDescription().getDescription());
			dto.setPrice(dto.getPrice());
			dto.setTypeCoin(dto.getTypeCoin());
			dto.setCategory(dto.getCategory());
			dto.setBrand(dto.getBrand());
			dto.setVolume(dto.getVolume());
			dto.setColor(dto.getColor());
			return dto;
		}

		throw new IllegalArgumentException("Tipo de produto desconhecido: " + product.getClass());
	}
}