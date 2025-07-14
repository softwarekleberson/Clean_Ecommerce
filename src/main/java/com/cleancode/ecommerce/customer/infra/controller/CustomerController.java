package com.cleancode.ecommerce.customer.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.customer.application.dtos.CreateChargeDto;
import com.cleancode.ecommerce.customer.application.dtos.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.CreateDeliveryDto;
import com.cleancode.ecommerce.customer.application.dtos.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomer;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerCharge;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerDelivery;
import com.cleancode.ecommerce.customer.application.useCase.ListCustomer;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CreateCustomer createCustomer;
	private final CreateCustomerDelivery createCustomerDelivery;
	private final CreateCustomerCharge createCustomerCharge;
	private final ListCustomer listCustomer;
	
	public CustomerController(CreateCustomer createCustomer, CreateCustomerDelivery createCustomerDelivery, CreateCustomerCharge createCustomerCharge, ListCustomer listCustomer) {
		this.createCustomer = createCustomer;
		this.createCustomerDelivery = createCustomerDelivery;
		this.createCustomerCharge = createCustomerCharge;
		this.listCustomer = listCustomer;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> createCustomer (@RequestBody CreateCustomerDto dto){
		createCustomer.execute(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}	
	
	@GetMapping("/list/customer/{id}")
	public ResponseEntity<ListCustomerDto> getCustomer (@PathVariable String id){
		ListCustomerDto customer = listCustomer.getCustomer(id);
		return ResponseEntity.ok(customer);
	}
	
	@PostMapping("/{id}/deliveries")
	public ResponseEntity<Void> createDelivery (@PathVariable String id, @RequestBody CreateDeliveryDto dto){
		createCustomerDelivery.execute(id, dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/{id}/charges")
	public ResponseEntity<Void> createCharge (@PathVariable String id, @RequestBody CreateChargeDto dto){
		createCustomerCharge.execute(id, dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}