package com.cleancode.ecommerce.product.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cleancode.ecommerce.product.domain.Product;

public interface ProductRepository {

	Product save(Product product);
	Page<Product> ListAllProductActive(Pageable pageable);
	List<Product> ListAllProductNotActive();
	Optional<Product> findById(String idProduct);
}
