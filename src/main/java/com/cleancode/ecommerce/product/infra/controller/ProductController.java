package com.cleancode.ecommerce.product.infra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.product.application.dto.input.CreateProductDto;
import com.cleancode.ecommerce.product.application.dto.input.ReviseDetailsDto;
import com.cleancode.ecommerce.product.application.dto.output.ListProductDto;
import com.cleancode.ecommerce.product.application.useCase.CreateProduct;
import com.cleancode.ecommerce.product.application.useCase.DeactivateProduct;
import com.cleancode.ecommerce.product.application.useCase.ListAllProduct;
import com.cleancode.ecommerce.product.application.useCase.ListProduct;
import com.cleancode.ecommerce.product.application.useCase.ReviseDetails;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final CreateProduct createProduct;
	private final ListAllProduct listAllProduct;
	private final ListProduct listProduct;
	private final ReviseDetails reviseDetails;
	private final DeactivateProduct deactivateProduct;

	public ProductController(CreateProduct createProduct, ListAllProduct listAllProduct, ListProduct listProduct,
			ReviseDetails reviseDetails ,DeactivateProduct deactivateProduct) {
		this.createProduct = createProduct;
		this.listAllProduct = listAllProduct;
		this.listProduct = listProduct;
		this.reviseDetails = reviseDetails;
		this.deactivateProduct = deactivateProduct;
	}

	@PostMapping
	public ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductDto dto) {
		createProduct.execute(dto);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ListProductDto> getProductById(@PathVariable String id) {
		return ResponseEntity.ok(listProduct.execute(id));
	}

	@GetMapping
	public ResponseEntity<List<ListProductDto>> getAllProducts() {
		return ResponseEntity.ok(listAllProduct.execute());
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<Void> reviseDetailsProduct(@Valid @RequestBody ReviseDetailsDto dto, @PathVariable String productId) {
		reviseDetails.execute(productId, dto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deactivateProduct(@PathVariable String productId) {
		deactivateProduct.execute(productId);
		return ResponseEntity.noContent().build();
	}
}