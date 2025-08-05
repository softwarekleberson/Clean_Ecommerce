package com.cleancode.ecommerce.stock.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.stock.application.dto.CreateInputStockDto;
import com.cleancode.ecommerce.stock.application.dto.CreateStockDto;
import com.cleancode.ecommerce.stock.application.dto.ListStockDto;
import com.cleancode.ecommerce.stock.application.usecase.CreateProductInput;
import com.cleancode.ecommerce.stock.application.usecase.CreateStock;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/stock")
public class StockController {

	private final CreateStock createStock;
	private final CreateProductInput createProductInput;
	
	public StockController(CreateStock createStock, CreateProductInput createProductInput) {
		this.createStock = createStock;
		this.createProductInput = createProductInput;
	}
	
	@PostMapping
	public ResponseEntity<ListStockDto> createStock (@Valid @RequestBody CreateStockDto dto){
		var created = createStock.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PostMapping("/input")
	public ResponseEntity<ListStockDto> productInput (@Valid @RequestBody CreateInputStockDto dto){
		var created = createProductInput.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
}