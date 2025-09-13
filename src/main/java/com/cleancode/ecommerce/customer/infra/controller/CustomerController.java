package com.cleancode.ecommerce.customer.infra.controller;

import java.util.List;

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

import com.cleancode.ecommerce.customer.application.dtos.address.CreateChargeDto;
import com.cleancode.ecommerce.customer.application.dtos.address.CreateDeliveryDto;
import com.cleancode.ecommerce.customer.application.dtos.address.UpdateAddressDto;
import com.cleancode.ecommerce.customer.application.dtos.card.CreateCardDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListAllCustomersDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.UpdateCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.UpdatePasswordDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.ChangeActivationStatusAdm;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomer;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomerCard;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomerCharge;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomerDelivery;
import com.cleancode.ecommerce.customer.application.useCase.contract.DeleteCharge;
import com.cleancode.ecommerce.customer.application.useCase.contract.DeleteDelivery;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListAllCustomers;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListCustomer;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdateCharge;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdateCustomer;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdateDelivery;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdatePassword;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

	private final CreateCustomer createCustomer;
	private final CreateCustomerDelivery createCustomerDelivery;
	private final CreateCustomerCharge createCustomerCharge;
	private final ListCustomer listCustomer;
	private final UpdateCustomer updateCustomer;
	private final UpdatePassword updatePassword;
	private final DeleteCharge deleteCharge;
	private final DeleteDelivery deleteDelivery;
	private final UpdateCharge updateCharge;
	private final UpdateDelivery updateDelivery;
	private final CreateCustomerCard createCard;
	private final ListAllCustomers listAllCustomers;
	private final ChangeActivationStatusAdm changeActivationStatusAdm;

	public CustomerController(CreateCustomer createCustomer, CreateCustomerDelivery createCustomerDelivery,
			CreateCustomerCharge createCustomerCharge, ListCustomer listCustomer, UpdateCustomer updateCustomer,
			UpdatePassword updatePassword, DeleteCharge deleteCharge, DeleteDelivery deleteDelivery,
			UpdateCharge updateCharge, UpdateDelivery updateDelivery, CreateCustomerCard createCard,
			ListAllCustomers listAllCustomers, ChangeActivationStatusAdm changeActivationStatusAdm) {
		
		this.createCustomer = createCustomer;
		this.createCustomerDelivery = createCustomerDelivery;
		this.createCustomerCharge = createCustomerCharge;
		this.listCustomer = listCustomer;
		this.updateCustomer = updateCustomer;
		this.updatePassword = updatePassword;
		this.deleteCharge = deleteCharge;
		this.deleteDelivery = deleteDelivery;
		this.updateCharge = updateCharge;
		this.updateDelivery = updateDelivery;
		this.createCard = createCard;
		this.listAllCustomers = listAllCustomers;
		this.changeActivationStatusAdm = changeActivationStatusAdm;
	}

	// ----------------------
	// Customers
	// ----------------------

	@PostMapping
	public ResponseEntity<ListCustomerDto> createCustomer(@Valid @RequestBody CreateCustomerDto dto) {
		var created = createCustomer.execute(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ListAllCustomersDto>> listAllCustomers() {
	    return ResponseEntity.ok(listAllCustomers.execute());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ListCustomerDto> getCustomer(@PathVariable String id) {
		return ResponseEntity.ok(listCustomer.execute(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ListCustomerDto> updateCustomer(@PathVariable String id, @RequestBody UpdateCustomerDto dto) {
		return ResponseEntity.ok(updateCustomer.execute(id, dto));
	}
	
	@PutMapping("/{id}/adm")
	public ResponseEntity<Void> changeActivationStatusAdm(@PathVariable String id) {
		changeActivationStatusAdm.execute(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}/password")
	public ResponseEntity<Void> updatePassword(@PathVariable String id, @Valid @RequestBody UpdatePasswordDto dto) {
		updatePassword.execute(id, dto);
		return ResponseEntity.noContent().build();
	}

	// ----------------------
	// Deliveries
	// ----------------------

	@PostMapping("/{customerId}/deliveries")
	public ResponseEntity<ListCustomerDto> addDelivery(@PathVariable String customerId,
			@Valid @RequestBody CreateDeliveryDto dto) {
		var updatedCustomer = createCustomerDelivery.execute(customerId, dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedCustomer);
	}

	@PutMapping("/{customerId}/deliveries/{deliveryId}")
	public ResponseEntity<ListCustomerDto> updateDelivery(@PathVariable String customerId,
			@PathVariable String deliveryId, @Valid @RequestBody UpdateAddressDto dto) {
		var updateCustomer = updateDelivery.execute(customerId, deliveryId, dto);
		return ResponseEntity.ok(updateCustomer);
	}

	@DeleteMapping("/{customerId}/deliveries/{deliveryId}")
	public ResponseEntity<Void> deleteDelivery(@PathVariable String customerId, @PathVariable String deliveryId) {
		deleteDelivery.execute(customerId, deliveryId);
		return ResponseEntity.noContent().build();
	}

	// ----------------------
	// Charges
	// ----------------------

	@PostMapping("/{customerId}/charges")
	public ResponseEntity<ListCustomerDto> addCharge(@PathVariable String customerId,
			@Valid @RequestBody CreateChargeDto dto) {
		var updatedCustomer = createCustomerCharge.execute(customerId, dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedCustomer);
	}

	@PutMapping("/{customerId}/charges/{chargeId}")
	public ResponseEntity<ListCustomerDto> updateCharge(@PathVariable String customerId, @PathVariable String chargeId,
			@Valid @RequestBody UpdateAddressDto dto) {
		var updateCustomer = updateCharge.execute(customerId, chargeId, dto);
		return ResponseEntity.ok(updateCustomer);
	}

	@DeleteMapping("/{customerId}/charges/{chargeId}")
	public ResponseEntity<Void> deleteCharge(@PathVariable String customerId, @PathVariable String chargeId) {
		deleteCharge.execute(customerId, chargeId);
		return ResponseEntity.noContent().build();
	}

	// ----------------------
	// Cards
	// ----------------------
	
	@PostMapping("/{customerId}/cards")
	public ResponseEntity<ListCustomerDto> addCard(@PathVariable String customerId,
			@Valid @RequestBody CreateCardDto dto) {
		var createNewCard = createCard.execute(customerId, dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createNewCard);
	}
}