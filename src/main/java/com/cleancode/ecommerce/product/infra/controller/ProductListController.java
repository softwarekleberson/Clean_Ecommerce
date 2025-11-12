package com.cleancode.ecommerce.product.infra.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.product.application.dto.output.ListProductDto;
import com.cleancode.ecommerce.product.application.useCase.contract.ListActiveProduct;
import com.cleancode.ecommerce.product.application.useCase.contract.ListAllProductActive;
import com.cleancode.ecommerce.shared.dto.PageResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("public/product")
@CrossOrigin(origins = "*")
public class ProductListController {

	private final ListAllProductActive listAllProduct;
	private final ListActiveProduct listProduct;
	
	public ProductListController(ListAllProductActive listAllProduct, ListActiveProduct listProduct) {
		this.listAllProduct = listAllProduct;
		this.listProduct = listProduct;
	}

    @Operation(summary = "Create new Adm.", security = {}) 
	@GetMapping("/{id}")
	public ResponseEntity<ListProductDto> getProductById(@PathVariable String id) {
		return ResponseEntity.ok(listProduct.execute(id));
	}

    @Operation(summary = "Create new Adm.", security = {}) 
	@GetMapping
	public ResponseEntity<PageResponse<ListProductDto>> getAllProducts(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "50") int size) {

	    Pageable pageable = PageRequest.of(page, size);
	    var result = listAllProduct.execute(pageable);
	    return ResponseEntity.ok(PageResponse.from(result));
	}
}