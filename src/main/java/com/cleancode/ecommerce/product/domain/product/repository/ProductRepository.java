package com.cleancode.ecommerce.product.domain.product.repository;

import java.util.List;

import com.cleancode.ecommerce.product.domain.product.Product;

public interface ProductRepository {

	void create(Product product);
	List<Product> listAllProduct();
}
