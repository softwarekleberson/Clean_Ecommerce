package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.ListCustomerDto;

public interface ListCustomer {

	public ListCustomerDto getCustomer(String customerId);
}
