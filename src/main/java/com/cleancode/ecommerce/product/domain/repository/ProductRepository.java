package com.cleancode.ecommerce.product.domain.repository;

import java.util.List;
import java.util.Optional;

import com.cleancode.ecommerce.product.domain.Product;

public interface ProductRepository {

	Product save(Product product);
	List<Product> listAll();
	Optional<Product> listProduct(String idProduct);
}
