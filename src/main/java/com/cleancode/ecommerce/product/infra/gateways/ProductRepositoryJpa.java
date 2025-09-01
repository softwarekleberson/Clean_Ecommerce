package com.cleancode.ecommerce.product.infra.gateways;

import java.util.List;
import java.util.Optional;

import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;
import com.cleancode.ecommerce.product.infra.mapper.ProductMapper;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.ProductEntity;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductRepositoryJpa implements ProductRepository {

	private final ProductJpa jpa;

	public ProductRepositoryJpa(ProductJpa jpa) {
		this.jpa = jpa;
	}

	@Transactional
	@Override
	public Product save(Product product) {
		ProductEntity entity = ProductMapper.toEntity(product);
		jpa.save(entity);
		Product domain = ProductMapper.toDomain(entity);
		return domain;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Product> ListAllProductActive() {
		List<ProductEntity> entity = jpa.findByActiveTrue();
		return entity.stream().map(ProductMapper::toDomain).toList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Product> ListAllProductNotActive() {
		List<ProductEntity> entity = jpa.findByActiveFalse();
		return entity.stream().map(ProductMapper::toDomain).toList();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Product> ListActiveProduct(String productId) {
		return jpa.findByProductIdAndActiveTrue(productId).map(ProductMapper::toDomain);
	}
}