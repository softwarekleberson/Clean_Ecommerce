package com.cleancode.ecommerce.product.infra.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cleancode.ecommerce.product.infra.persistence.jpa.product.ProductEntity;

public interface ProductJpa extends JpaRepository<ProductEntity, String> {

	List<ProductEntity> findByActiveTrue();

	List<ProductEntity> findByActiveFalse();
	
	@Query("SELECT p FROM ProductEntity p " + "WHERE p.product_id = :productId AND p.active = true")
	Optional<ProductEntity> findByProductIdAndActiveTrue(@Param("productId") String productId);

}