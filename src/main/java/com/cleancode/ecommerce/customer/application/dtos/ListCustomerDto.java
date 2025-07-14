package com.cleancode.ecommerce.customer.application.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.shared.kernel.Email;

public record ListCustomerDto(
		
		String id,
		String name,
		Gender gender,
		Email email,
		Phone phone,
		List<ListDeliveryDto> deliveres,
		List<ListChargeDto> charges
		
		) {

	public ListCustomerDto(Customer customer) {
		this(customer.getId().getValue(),
			 customer.getName().getName(),
			 customer.getGender(),
			 customer.getContact().getEmail(),
			 customer.getContact().getFullPhone(),
			 
			 customer.getDeliverys().stream().map(ListDeliveryDto::new)
			 .collect(Collectors.toList()),
			 
			 customer.getCharges().stream().map(ListChargeDto::new)
			 .collect(Collectors.toList())
			);
	}
}