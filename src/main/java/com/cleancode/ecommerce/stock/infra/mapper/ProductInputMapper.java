package com.cleancode.ecommerce.stock.infra.mapper;

import com.cleancode.ecommerce.stock.domain.ProductInput;
import com.cleancode.ecommerce.stock.domain.ProductQuality;
import com.cleancode.ecommerce.stock.infra.persistence.ProductInputEntity;
import com.cleancode.ecommerce.stock.infra.persistence.ProductQualityEntity;
import com.cleancode.ecommerce.stock.infra.persistence.StockEntity;

public class ProductInputMapper {

	public static ProductInput toDomain(ProductInputEntity entity) {
		return new ProductInput(entity.getQuantity(), ProductQuality.valueOf(entity.getProductQuality().name()),
				entity.getPurchasePrice(), entity.getSupplier());
	}

	public static ProductInputEntity toEntity(ProductInput domain, StockEntity stockEntity) {
		ProductInputEntity entity = new ProductInputEntity();
		entity.setQuantity(domain.getQuantity().getQuantity());
		entity.setProductQuality(ProductQualityEntity.valueOf(domain.getProductQuality().name()));
		entity.setPurchasePrice(domain.getPurchasePrice().getPurchasePrice());
		entity.setSupplier(domain.getSupplier().getSupplier());
		entity.setStock(stockEntity);
		return entity;
	}
}