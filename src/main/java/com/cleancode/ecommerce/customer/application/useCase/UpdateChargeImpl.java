package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.address.UpdateAddressDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdateCharge;
import com.cleancode.ecommerce.customer.domain.customer.Charge;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class UpdateChargeImpl implements UpdateCharge{

	private final CustomerRepository repository;

	public UpdateChargeImpl(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public ListCustomerDto execute(String clienteId, String id, UpdateAddressDto dto) {
		Customer customer = repository.getCustomerById(clienteId).orElseThrow(() -> new IllegalDomainException("Customer with id : " + id + " not found"));				
		Charge charge = customer.findChargeById(id);
		charge.update(dto.receiver(), dto.main() ,dto.street(), dto.number(), dto.neighborhood(), dto.zipCode(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city(), dto.state(), dto.country());
		
		repository.save(customer);
		return new ListCustomerDto(customer);
	}
}