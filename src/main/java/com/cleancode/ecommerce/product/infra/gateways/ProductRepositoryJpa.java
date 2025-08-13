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
	public List<Product> listAll() {
		List<ProductEntity> entity = jpa.findAll();
		return entity.stream()
				   .map(ProductMapper::toDomain)
				   .toList();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Product> listProduct(String idProduct) {
		return jpa.findById(idProduct).map(ProductMapper::toDomain);  
	}
}