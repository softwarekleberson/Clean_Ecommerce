package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.card.CreateCardDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomerCard;
import com.cleancode.ecommerce.customer.domain.card.Card;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class CreateCardImpl implements CreateCustomerCard {

	private final CustomerRepository repository;

	public CreateCardImpl(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public ListCustomerDto execute(String email, CreateCardDto dto) {
		Customer customer = repository.findByEmail(email)
				.orElseThrow(() -> new IllegalDomainException("Customer not found"));
		Card card = dto.createCard();

		customer.registerCard(card);
		repository.save(customer);
		return new ListCustomerDto(customer);
	}
}