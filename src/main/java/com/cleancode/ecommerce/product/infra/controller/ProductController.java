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
import com.cleancode.ecommerce.product.application.dto.input.ModifySellingPriceDto;
import com.cleancode.ecommerce.product.application.dto.input.ProductStatusChangeDto;
import com.cleancode.ecommerce.product.application.dto.input.ReviseDetailsDto;
import com.cleancode.ecommerce.product.application.dto.output.ListProductDto;
import com.cleancode.ecommerce.product.application.useCase.ManualProductActivation;
import com.cleancode.ecommerce.product.application.useCase.CreateProduct;
import com.cleancode.ecommerce.product.application.useCase.IncreaseSellingPriceAboveProfitMargin;
import com.cleancode.ecommerce.product.application.useCase.ManualProductDeactivation;
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
	private final ManualProductDeactivation manualProductDeactivation;
	private final ManualProductActivation manualProductActivation;
	private final IncreaseSellingPriceAboveProfitMargin increaseSellingPriceAboveProfitMargin;

	public ProductController(CreateProduct createProduct, ListAllProduct listAllProduct, ListProduct listProduct,
			ReviseDetails reviseDetails, ManualProductDeactivation manualProductDeactivation,
			ManualProductActivation manualProductActivation,
			IncreaseSellingPriceAboveProfitMargin increaseSellingPriceAboveProfitMargin) {
		this.createProduct = createProduct;
		this.listAllProduct = listAllProduct;
		this.listProduct = listProduct;
		this.reviseDetails = reviseDetails;
		this.manualProductDeactivation = manualProductDeactivation;
		this.manualProductActivation = manualProductActivation;
		this.increaseSellingPriceAboveProfitMargin = increaseSellingPriceAboveProfitMargin;
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
	public ResponseEntity<Void> reviseDetailsProduct(@Valid @RequestBody ReviseDetailsDto dto,
			@PathVariable String productId) {
		reviseDetails.execute(productId, dto);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{productId}/activate")
	public ResponseEntity<Void> activateProduct(@Valid @RequestBody ProductStatusChangeDto dto,
			@PathVariable String productId) {
		manualProductActivation.execute(productId, dto);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{productId}/selling/price")
	public ResponseEntity<Void> priceAboveProfitMargin(@Valid @RequestBody ModifySellingPriceDto dto,
			@PathVariable String productId) {
		increaseSellingPriceAboveProfitMargin.execute(productId, dto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{productId}/disable")
	public ResponseEntity<Void> deactivateProduct(@Valid @RequestBody ProductStatusChangeDto dto,
			@PathVariable String productId) {
		manualProductDeactivation.execute(productId, dto);
		return ResponseEntity.noContent().build();
	}
}