package com.cleancode.ecommerce.stock.infra.mapper;

import com.cleancode.ecommerce.product.domain.IdProduct;
import com.cleancode.ecommerce.stock.domain.OrderId;
import com.cleancode.ecommerce.stock.domain.ProductOutput;
import com.cleancode.ecommerce.stock.infra.persistence.ProductOutputEntity;
import com.cleancode.ecommerce.stock.infra.persistence.StockEntity;

public class ProductOutputMapper {

	public static ProductOutput toDoaim(ProductOutputEntity entity) {
		return new ProductOutput(new OrderId(entity.getOrderId()), new IdProduct(entity.getProductId()), entity.getQuantity());
	}

	public static ProductOutputEntity toEntity(ProductOutput domain, StockEntity stockEntity) {
		ProductOutputEntity entity = new ProductOutputEntity();
		entity.setOrderId(domain.getOrderId().getOrderId());
		entity.setProductId(domain.getProductId().getIdProduct());
		entity.setQuantity(domain.getQuantity());
		entity.setStock(stockEntity);
		return entity;
	}
}