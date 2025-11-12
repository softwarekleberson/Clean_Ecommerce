package com.cleancode.ecommerce.product.infra.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cleancode.ecommerce.product.infra.persistence.jpa.product.ProductEntity;

public interface ProductJpa extends JpaRepository<ProductEntity, String> {

	Page<ProductEntity> findByActiveTrue(Pageable pageable);

	List<ProductEntity> findByActiveFalse();
	
	Optional<ProductEntity> findById(String productId);

}