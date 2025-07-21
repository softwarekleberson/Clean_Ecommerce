package com.cleancode.ecommerce.product.infra.gateways;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleancode.ecommerce.product.infra.persistence.jpa.product.ProductEntity;

public interface ProductJpa extends JpaRepository<ProductEntity, UUID>{

}
