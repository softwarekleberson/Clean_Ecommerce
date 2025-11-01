package com.cleancode.ecommerce.adm.infra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.adm.application.contract.CreateVoucher;
import com.cleancode.ecommerce.adm.application.dto.voucher.CreateVoucherDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ChangeStatusCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListAllCustomersDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.ChangeActivationStatusAdm;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListAllCustomers;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adm/customers")
@CrossOrigin(origins = "*")
public class AdmCustomerController {

	private final ListAllCustomers listAllCustomers;
	private final ChangeActivationStatusAdm changeActivationStatusAdm;
	private final CreateVoucher createVoucher; 
	
	public AdmCustomerController(ListAllCustomers listAllCustomers,
			ChangeActivationStatusAdm changeActivationStatusAdm,
			CreateVoucher createVoucher) {
		
		this.listAllCustomers = listAllCustomers;
		this.changeActivationStatusAdm = changeActivationStatusAdm;
		this.createVoucher = createVoucher;
	}

	@PostMapping("/voucher")
	public ResponseEntity<Void> createVoucher(@Valid @RequestBody CreateVoucherDto dto) {
		createVoucher.execute(dto);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<ListAllCustomersDto>> listAllCustomers() {
		return ResponseEntity.ok(listAllCustomers.execute());
	}

	@PutMapping("/change-status")
	public ResponseEntity<Void> changeCustomerActivationStatus(@Valid @RequestBody ChangeStatusCustomerDto dto) {
		changeActivationStatusAdm.execute(dto.customerId());
		return ResponseEntity.noContent().build();
	}
}
