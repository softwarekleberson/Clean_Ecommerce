package com.cleancode.ecommerce.product.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.product.application.dto.CreateProductDto;
import com.cleancode.ecommerce.product.application.useCase.CreateProduct;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final CreateProduct createProduct;
	
	public ProductController(CreateProduct createProduct) {
		this.createProduct = createProduct;
	}

	@PostMapping
	public ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductDto dto) {
		createProduct.execute(dto);
		return ResponseEntity.noContent().build();
	}
}