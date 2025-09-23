package com.cleancode.ecommerce.customer.infra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.customer.application.dtos.customer.ChangeStatusCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListAllCustomersDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.ChangeActivationStatusAdm;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListAllCustomers;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adm")
@CrossOrigin(origins = "*")
public class AdmController {

	private final ListAllCustomers listAllCustomers;
	private final ChangeActivationStatusAdm changeActivationStatusAdm;

	public AdmController(ListAllCustomers listAllCustomers, ChangeActivationStatusAdm changeActivationStatusAdm) {
		this.listAllCustomers = listAllCustomers;
		this.changeActivationStatusAdm = changeActivationStatusAdm;
	}

	@GetMapping("/customers")
	public ResponseEntity<List<ListAllCustomersDto>> listAllCustomers() {
		return ResponseEntity.ok(listAllCustomers.execute());
	}

	@PutMapping("/customers/change-status")
	public ResponseEntity<Void> changeActivationStatusAdm(@Valid @RequestBody ChangeStatusCustomerDto dto) {
		changeActivationStatusAdm.execute(dto.customerId());
		return ResponseEntity.noContent().build();
	}
}
