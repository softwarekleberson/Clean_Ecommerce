package com.cleancode.ecommerce.customer.infra.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.customer.application.dtos.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.CreateDeliveryDto;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomer;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerDelivery;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CreateCustomer createCustomer;
	private final CreateCustomerDelivery createCustomerDelivery;
	
	public CustomerController(CreateCustomer createCustomer, CreateCustomerDelivery createCustomerDelivery) {
		this.createCustomer = createCustomer;
		this.createCustomerDelivery = createCustomerDelivery;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> createCustomer (@RequestBody CreateCustomerDto dto){
		createCustomer.execute(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}	
	
	@PostMapping("/{id}/deliveries")
	public ResponseEntity<Void> createDelivery (@PathVariable UUID id, @RequestBody CreateDeliveryDto dto){
		createCustomerDelivery.execute(id, dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}