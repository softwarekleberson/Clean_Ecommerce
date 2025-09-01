package com.cleancode.ecommerce.order.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.order.application.dtos.input.CreateCartDto;
import com.cleancode.ecommerce.order.application.dtos.output.ListCartDto;
import com.cleancode.ecommerce.order.application.useCase.contract.AddProductToCart;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carts")
public class CartController {

	private AddProductToCart addProductToCart;
	
	public CartController(AddProductToCart addProductToCart) {
		this.addProductToCart = addProductToCart;
	}
	
	@PostMapping
	public ResponseEntity<ListCartDto> createCart (@Valid @RequestBody CreateCartDto dto) {
		var create = addProductToCart.execute(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(create);
	}
}