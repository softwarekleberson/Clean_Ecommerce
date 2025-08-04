package com.cleancode.ecommerce.stock.infra.gateways;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cleancode.ecommerce.stock.domain.Stock;
import com.cleancode.ecommerce.stock.domain.repository.StockRepository;
import com.cleancode.ecommerce.stock.infra.mapper.StockMapper;
import com.cleancode.ecommerce.stock.infra.persistence.StockEntity;

@Repository
public class StockRepositoryJpa implements StockRepository {

	private final StockJpa jpa;

	public StockRepositoryJpa(StockJpa jpa) {
		this.jpa = jpa;
	}

	@Transactional
	@Override
	public Stock create(Stock stoke) {
		StockEntity entity = StockMapper.toEntity(stoke);
		jpa.save(entity);
		Stock domain = StockMapper.toDomain(entity);
		return domain;
	}

	@Override
	public Stock getStock(String id) {
		StockEntity entity = jpa.findByProductId(id).orElseThrow(() -> new RuntimeException("Stock not found"));
		return StockMapper.toDomain(entity);
	}
}