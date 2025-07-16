package com.cleancode.ecommerce.customer.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.customer.application.dtos.address.CreateChargeDto;
import com.cleancode.ecommerce.customer.application.dtos.address.CreateDeliveryDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.UpdateCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.UpdatePasswordDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomer;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomerCharge;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomerDelivery;
import com.cleancode.ecommerce.customer.application.useCase.contract.DeleteCharge;
import com.cleancode.ecommerce.customer.application.useCase.contract.DeleteDelivery;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListCustomer;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdateCustomer;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdatePassword;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CreateCustomer createCustomer;
	private final CreateCustomerDelivery createCustomerDelivery;
	private final CreateCustomerCharge createCustomerCharge;
	private final ListCustomer listCustomer;
	private final UpdateCustomer updateCustomer;
	private final UpdatePassword updatePassword;
	private final DeleteCharge deleteCharge;
	private final DeleteDelivery deleteDelivery;
	
	public CustomerController(CreateCustomer createCustomer, CreateCustomerDelivery createCustomerDelivery, CreateCustomerCharge createCustomerCharge, ListCustomer listCustomer, UpdateCustomer updateCustomer, UpdatePassword updatePassword, DeleteCharge deleteCharge, DeleteDelivery deleteDelivery) {
		this.createCustomer = createCustomer;
		this.createCustomerDelivery = createCustomerDelivery;
		this.createCustomerCharge = createCustomerCharge;
		this.listCustomer = listCustomer;
		this.updateCustomer = updateCustomer;
		this.updatePassword = updatePassword;
		this.deleteCharge = deleteCharge;
		this.deleteDelivery = deleteDelivery;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ListCustomerDto> createCustomer (@RequestBody CreateCustomerDto dto){
		ListCustomerDto customer = createCustomer.execute(dto);
		return ResponseEntity.ok(customer);
	}	
	
	@GetMapping("/list/{id}")
	public ResponseEntity<ListCustomerDto> getCustomer (@PathVariable String id){
		ListCustomerDto customer = listCustomer.execute(id);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ListCustomerDto> updateCustomer (@PathVariable String id, @RequestBody UpdateCustomerDto dto){
		ListCustomerDto customer = updateCustomer.execute(id, dto);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping("/update/password/{id}")
	public ResponseEntity<Void> updatePassword (@PathVariable String id, @RequestBody UpdatePasswordDto dto){
		updatePassword.execute(id, dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/{id}/deliveries")
	public ResponseEntity<ListCustomerDto> createDelivery (@PathVariable String id, @RequestBody CreateDeliveryDto dto){
		ListCustomerDto customer = createCustomerDelivery.execute(id, dto);
		return ResponseEntity.ok(customer);
	}
	
	@PostMapping("/{id}/charges")
	public ResponseEntity<ListCustomerDto> createCharge (@PathVariable String id, @RequestBody CreateChargeDto dto){
		ListCustomerDto customer = createCustomerCharge.execute(id, dto);
		return ResponseEntity.ok(customer);
	}
	
	@DeleteMapping("/{clienteId}/charges/{id}")
	public ResponseEntity<Void> deleteCharge (@PathVariable String clienteId, @PathVariable String id){
		deleteCharge.execute(clienteId, id);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/{clienteId}/deliveres/{id}")
	public ResponseEntity<Void> deleteDelivery (@PathVariable String clienteId, @PathVariable String id){
		deleteDelivery.execute(clienteId, id);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}