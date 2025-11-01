package com.cleancode.ecommerce.order.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.order.application.dtos.input.CreateCartDto;
import com.cleancode.ecommerce.order.application.dtos.input.DeleteUniqueProductToCartDto;
import com.cleancode.ecommerce.order.application.dtos.input.UpdateCartDto;
import com.cleancode.ecommerce.order.application.dtos.output.CartDto;
import com.cleancode.ecommerce.order.application.useCase.contract.AddProductToCart;
import com.cleancode.ecommerce.order.application.useCase.contract.DeleteAllCart;
import com.cleancode.ecommerce.order.application.useCase.contract.DeleteUniqueProductCart;
import com.cleancode.ecommerce.order.application.useCase.contract.ListCart;
import com.cleancode.ecommerce.order.application.useCase.contract.UpdateCart;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class CartController {

	private final AddProductToCart addProductToCart;
	private final ListCart listCart;
	private final UpdateCart updateCart;
	private final DeleteAllCart deleteAllCart;
	private final DeleteUniqueProductCart deleteUniqueProductCart;

	public CartController(AddProductToCart addProductToCart, ListCart listCart, UpdateCart updateCart, DeleteAllCart deleteAllCart, DeleteUniqueProductCart deleteUniqueProductCart) {
		this.addProductToCart = addProductToCart;
		this.listCart = listCart;
		this.updateCart = updateCart;
		this.deleteAllCart = deleteAllCart;
		this.deleteUniqueProductCart = deleteUniqueProductCart;
	}

	@PostMapping
	public ResponseEntity<CartDto> createCart(@Valid @RequestBody CreateCartDto dto) {
		var create = addProductToCart.execute(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(create);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CartDto> getCart(@PathVariable String customerId) {
		return ResponseEntity.ok(listCart.execute(customerId));
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<CartDto> updateCart(@PathVariable String customerId, @Valid @RequestBody UpdateCartDto dto) {
		var update = updateCart.execute(customerId, dto);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping("/{customerId}/item")
	public ResponseEntity<Void> deleteProductCart(@PathVariable String customerId, @Valid @RequestBody DeleteUniqueProductToCartDto dto) {
		deleteUniqueProductCart.execute(customerId, dto);
		return ResponseEntity.noContent().build();
	}
	
	/*
	@DeleteMapping("/{customerId}/items")
	public ResponseEntity<Void> deleteAllCart(@PathVariable String customerId) {
		deleteAllCart.execute(customerId);
		return ResponseEntity.noContent().build();
	}
	*/
}