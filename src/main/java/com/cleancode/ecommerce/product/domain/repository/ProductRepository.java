package com.cleancode.ecommerce.product.domain.repository;

import java.util.List;

import com.cleancode.ecommerce.product.domain.Product;

public interface ProductRepository {

	Product create(Product product);
	List<Product> listAllProduct();
	Product listProduct(String idProduct);
}
