package com.cleancode.ecommerce.stock.infra.gateways;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleancode.ecommerce.stock.infra.persistence.StockEntity;

public interface StockJpa extends JpaRepository<StockEntity, String>{

	Optional<StockEntity> findByProductId(String id);
}
