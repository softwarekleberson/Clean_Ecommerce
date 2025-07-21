package com.cleancode.ecommerce.product.infra.mapper;

import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.bag.Bag;
import com.cleancode.ecommerce.product.infra.mapper.bag.BagMapper;
import com.cleancode.ecommerce.product.infra.persistence.jpa.bag.BagEntity;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.ProductEntity;

public class ProductMapper {

	private ProductMapper() {
	}

	public static Product toDomain(ProductEntity entity) {
		if (entity instanceof BagEntity) {
			return BagMapper.toDomain((BagEntity) entity);
		}

		throw new UnsupportedOperationException("Type product not suport: " + entity.getClass());
	}

	public static ProductEntity toEntity(Product domain) {
		if (domain instanceof Bag) {
			return BagMapper.toEntity((Bag) domain);
		}

		throw new UnsupportedOperationException("Type product not suport: " + domain.getClass());
	}
}